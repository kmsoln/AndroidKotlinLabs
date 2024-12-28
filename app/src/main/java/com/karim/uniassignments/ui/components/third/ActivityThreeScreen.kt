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
fun ActivityThreeScreen(navController: NavController, receivedMessage: String) {
    val message = remember { mutableStateOf("Hello from Activity Three!") }

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
            navController.navigate("lab_3/${message.value}")
        }) {
            Text("Go back to Activity One")
        }
    }
}
