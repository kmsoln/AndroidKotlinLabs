package com.karim.uniassignments.ui.components.fourth.sensor

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.karim.uniassignments.presentation.screens.fourth.sensor.rememberSensorData

@Composable
fun SensorScreen(navController: NavController) {
    val context = LocalContext.current
    val sensorData = rememberSensorData(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SensorDataSection(title = "Акселерометр", data = sensorData.accelerometerValues)
        SensorDataSection(title = "Магнитный сенсор", data = sensorData.magneticFieldValues)
        SensorDataSection(title = "Приближение", data = listOf(sensorData.proximity))
        SensorDataSection(title = "Освещенность", data = listOf(sensorData.light))

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate("compassScreen") }) {
            Text("Compass")
        }
    }
}

@Composable
fun SensorDataSection(title: String, data: List<Float>) {
    Text(title, fontSize = 18.sp)
    data.forEach { value ->
        Text(value.toString(), fontSize = 16.sp)
    }
}
