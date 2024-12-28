package com.karim.uniassignments.ui.components.eighth

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.Executor

@Composable
fun EightExampleOne() {
    val context = LocalContext.current
    val cameraExecutor: Executor = ContextCompat.getMainExecutor(context)

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            PreviewView(ctx).apply {
                // Use FILL_CENTER to avoid stretching and maintain aspect ratio
                scaleType = PreviewView.ScaleType.FILL_CENTER

                // Ensure this happens on the main thread
                post {
                    setupCamera(ctx, this, cameraExecutor)
                }
            }
        }
    )
}

private fun setupCamera(context: Context, previewView: PreviewView, cameraExecutor: Executor) {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

    cameraProviderFuture.addListener({
        try {
            val cameraProvider = cameraProviderFuture.get()

            // Create a Preview use case
            val preview = Preview.Builder().build().also {
                it.surfaceProvider = previewView.surfaceProvider
            }

            // Select the back camera
            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()

            // Unbind previous use cases and bind the new ones
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                context as LifecycleOwner, // Ensure context implements LifecycleOwner
                cameraSelector,
                preview
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }, cameraExecutor)
}