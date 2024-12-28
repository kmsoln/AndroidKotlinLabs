package com.karim.uniassignments.presentation.screens.fourth.compass

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.*
import com.karim.uniassignments.model.fourth.compass.CompassSensorData

@Composable
fun rememberCompassSensorData(context: Context): CompassSensorData {
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }

    // Mutable states to store compass-related data
    var xPosition by remember { mutableStateOf(0f) }
    var yPosition by remember { mutableStateOf(0f) }
    var compassRotation by remember { mutableStateOf(0f) }

    DisposableEffect(Unit) {
        val sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                when (event.sensor.type) {
                    Sensor.TYPE_ACCELEROMETER -> {
                        xPosition = -event.values[0] * 20
                        yPosition = event.values[1] * 20
                    }
                    Sensor.TYPE_MAGNETIC_FIELD -> {
                        compassRotation = event.values[0]
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        accelerometer?.also { sensorManager.registerListener(sensorEventListener, it, SensorManager.SENSOR_DELAY_UI) }
        magneticField?.also { sensorManager.registerListener(sensorEventListener, it, SensorManager.SENSOR_DELAY_UI) }

        onDispose {
            sensorManager.unregisterListener(sensorEventListener)
        }
    }

    return CompassSensorData(
        xPosition = xPosition,
        yPosition = yPosition,
        compassRotation = compassRotation
    )
}
