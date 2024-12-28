package com.karim.uniassignments.presentation.screens.fourth.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.*
import com.karim.uniassignments.model.fourth.sensor.SensorData

@Composable
fun rememberSensorData(context: Context): SensorData {
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }

    var accelerometerValues by remember { mutableStateOf(listOf(0f, 0f, 0f)) }
    var magneticFieldValues by remember { mutableStateOf(listOf(0f, 0f, 0f)) }
    var proximity by remember { mutableStateOf(0f) }
    var light by remember { mutableStateOf(0f) }

    DisposableEffect(Unit) {
        val sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                when (event.sensor.type) {
                    Sensor.TYPE_ACCELEROMETER -> {
                        accelerometerValues = event.values.toList()
                    }
                    Sensor.TYPE_MAGNETIC_FIELD -> {
                        magneticFieldValues = event.values.toList()
                    }
                    Sensor.TYPE_PROXIMITY -> {
                        proximity = event.values[0]
                    }
                    Sensor.TYPE_LIGHT -> {
                        light = event.values[0]
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val magneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        val proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        val lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        accelerometer?.also { sensorManager.registerListener(sensorEventListener, it, SensorManager.SENSOR_DELAY_UI) }
        magneticField?.also { sensorManager.registerListener(sensorEventListener, it, SensorManager.SENSOR_DELAY_UI) }
        proximitySensor?.also { sensorManager.registerListener(sensorEventListener, it, SensorManager.SENSOR_DELAY_UI) }
        lightSensor?.also { sensorManager.registerListener(sensorEventListener, it, SensorManager.SENSOR_DELAY_UI) }

        onDispose {
            sensorManager.unregisterListener(sensorEventListener)
        }
    }

    return SensorData(
        accelerometerValues = accelerometerValues,
        magneticFieldValues = magneticFieldValues,
        proximity = proximity,
        light = light
    )
}
