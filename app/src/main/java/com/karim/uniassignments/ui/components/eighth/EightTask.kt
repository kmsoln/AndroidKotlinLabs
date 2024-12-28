package com.karim.uniassignments.ui.components.eighth

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.*
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor

private const val REQUEST_CODE_PERMISSIONS = 10
private val REQUIRED_PERMISSIONS = arrayOf(
    android.Manifest.permission.CAMERA,
    android.Manifest.permission.RECORD_AUDIO
)

@Composable
fun EightTask() {
    val context = LocalContext.current
    val cameraExecutor: Executor = ContextCompat.getMainExecutor(context)

    // State to track recording instance and permissions
    val currentRecording = remember { mutableStateOf<Recording?>(null) }
    val permissionsGranted = remember { mutableStateOf(false) }

    // Check and request permissions
    LaunchedEffect(Unit) {
        permissionsGranted.value = checkAndRequestPermissions(context)
    }

    if (permissionsGranted.value) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Camera Preview with Control Buttons
            CameraPreviewWithControls(context, cameraExecutor, currentRecording)
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            BasicText(
                text = "Please grant camera and audio permissions to use this feature.",
                style = TextStyle(fontSize = 18.sp)
            )
        }
    }
}

private fun checkAndRequestPermissions(context: Context): Boolean {
    val missingPermissions = REQUIRED_PERMISSIONS.filter {
        ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
    }

    if (missingPermissions.isEmpty()) {
        return true
    }

    ActivityCompat.requestPermissions(
        context as Activity,
        missingPermissions.toTypedArray(),
        REQUEST_CODE_PERMISSIONS
    )
    return false
}

@Composable
fun CameraPreviewWithControls(
    context: Context,
    cameraExecutor: Executor,
    currentRecording: MutableState<Recording?>
) {
    val lifecycleOwner = LocalContext.current as LifecycleOwner

    // VideoCapture and ImageCapture states
    val videoCapture = remember { mutableStateOf<VideoCapture<Recorder>?>(null) }
    val imageCapture = remember { mutableStateOf<ImageCapture?>(null) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Camera Preview
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            factory = { ctx ->
                PreviewView(ctx).apply {
                    scaleType = PreviewView.ScaleType.FILL_CENTER
                    post {
                        setupCamera(ctx, this, lifecycleOwner, videoCapture, imageCapture)
                    }
                }
            }
        )

        // Control Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Take Photo Button
            Button(onClick = {
                imageCapture.value?.let {
                    takePhoto(context, it)
                }
            }) {
                BasicText(text = "Take Photo", style = TextStyle(fontSize = 16.sp))
            }

            // Record Video Button
            Button(onClick = {
                val recording = currentRecording.value
                if (recording != null) {
                    stopRecording(recording)
                    currentRecording.value = null
                } else {
                    videoCapture.value?.let { vc ->
                        currentRecording.value = startRecording(context, vc)
                    }
                }
            }) {
                BasicText(
                    text = if (currentRecording.value != null) "Stop Recording" else "Record Video",
                    style = TextStyle(fontSize = 16.sp)
                )
            }
        }
    }
}

private fun setupCamera(
    context: Context,
    previewView: PreviewView,
    lifecycleOwner: LifecycleOwner,
    videoCaptureState: MutableState<VideoCapture<Recorder>?>,
    imageCaptureState: MutableState<ImageCapture?>
) {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
    cameraProviderFuture.addListener({
        val cameraProvider = cameraProviderFuture.get()

        // Preview Use Case
        val preview = Preview.Builder()
            .build()
            .also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

        // Video Capture Use Case
        val recorder = Recorder.Builder()
            .setQualitySelector(QualitySelector.from(Quality.HIGHEST))
            .build()
        val videoCapture = VideoCapture.withOutput(recorder)
        videoCaptureState.value = videoCapture

        // Image Capture Use Case
        val imageCapture = ImageCapture.Builder().build()
        imageCaptureState.value = imageCapture

        // Camera Selector
        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        // Bind use cases to the lifecycle
        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                videoCapture,
                imageCapture
            )
        } catch (e: Exception) {
            Log.e("CameraX", "Use case binding failed", e)
        }
    }, ContextCompat.getMainExecutor(context))
}

private fun takePhoto(context: Context, imageCapture: ImageCapture) {
    val name = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        .format(System.currentTimeMillis())
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
    }

    val outputOptions = ImageCapture.OutputFileOptions.Builder(
        context.contentResolver,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        contentValues
    ).build()

    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                Log.d("CameraX", "Photo saved: ${outputFileResults.savedUri}")
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e("CameraX", "Photo capture failed: ${exception.message}", exception)
            }
        }
    )
}

private fun startRecording(context: Context, videoCapture: VideoCapture<Recorder>): Recording? {
    // Check if RECORD_AUDIO permission is granted
    val hasAudioPermission = ContextCompat.checkSelfPermission(
        context, android.Manifest.permission.RECORD_AUDIO
    ) == PackageManager.PERMISSION_GRANTED

    // File name and output details
    val name = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        .format(System.currentTimeMillis())
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_MOVIES)
    }

    val mediaStoreOutput = MediaStoreOutputOptions.Builder(
        context.contentResolver,
        MediaStore.Video.Media.EXTERNAL_CONTENT_URI
    ).setContentValues(contentValues).build()

    return try {
        // Prepare the recording
        val recording = videoCapture.output
            .prepareRecording(context, mediaStoreOutput)
            .apply {
                if (hasAudioPermission) {
                    withAudioEnabled() // Only enable audio if permission is granted
                }
            }
            .start(ContextCompat.getMainExecutor(context)) { recordEvent ->
                when (recordEvent) {
                    is VideoRecordEvent.Start -> Log.d("CameraX", "Recording started")
                    is VideoRecordEvent.Finalize -> {
                        if (recordEvent.hasError()) {
                            Log.e("CameraX", "Recording error: ${recordEvent.error}")
                        } else {
                            Log.d("CameraX", "Video saved: ${recordEvent.outputResults.outputUri}")
                        }
                    }
                }
            }

        recording
    } catch (e: SecurityException) {
        Log.e("CameraX", "Recording failed due to missing permissions: ${e.message}", e)
        null // Return null if permission is missing
    }
}

private fun stopRecording(recording: Recording) {
    recording.stop()
}