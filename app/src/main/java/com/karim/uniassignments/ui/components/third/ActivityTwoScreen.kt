package com.karim.uniassignments.ui.components.third

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ActivityTwoScreen(navController: NavController, receivedMessage: String) {
    val message = remember { mutableStateOf("Hello from Activity Two!") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Received: $receivedMessage")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("activityThree/${message.value}")
        }) {
            Text("Go to Activity Three")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Back to Activity One")
        }
    }
}
