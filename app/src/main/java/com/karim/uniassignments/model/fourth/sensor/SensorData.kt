package com.karim.uniassignments.model.fourth.sensor

data class SensorData(
    val accelerometerValues: List<Float>,
    val magneticFieldValues: List<Float>,
    val proximity: Float,
    val light: Float
)