package com.karim.uniassignments.ui.components.fourth.compass

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.karim.uniassignments.model.fourth.compass.CompassSensorData
import com.karim.uniassignments.presentation.screens.fourth.compass.rememberCompassSensorData

@Composable
fun CompassScreen() {
    val context = LocalContext.current
    val sensorData = rememberCompassSensorData(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Rotate the phone to move the circle", fontSize = 20.sp)

        Box(
            modifier = Modifier
                .size(300.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            MovingCircle(sensorData.xPosition, sensorData.yPosition)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Compass", fontSize = 20.sp)
            Compass(rotation = sensorData.compassRotation)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCompassScreen() {

    val mockSensorData = CompassSensorData(
        xPosition = 150f,
        yPosition = 150f,
        compassRotation = 90f
    )


    CompassScreenWithMockData(mockSensorData)
}

@Composable
fun CompassScreenWithMockData(sensorData: CompassSensorData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Rotate the phone to move the circle", fontSize = 20.sp)

        Box(
            modifier = Modifier
                .size(300.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            MovingCircle(sensorData.xPosition, sensorData.yPosition)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Compass", fontSize = 20.sp)
            Compass(rotation = sensorData.compassRotation)
        }
    }
    }
