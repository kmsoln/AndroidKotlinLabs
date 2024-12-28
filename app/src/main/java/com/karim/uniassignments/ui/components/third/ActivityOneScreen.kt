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
fun ActivityOneScreen(navController: NavController, receivedName: String) {
    val message = remember { mutableStateOf("Hello, my name is ${receivedName}\ntelling you my name from Activity One!") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message.value)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            navController.navigate("activityTwo/${message.value}")
        }) {
            Text("Go to Activity Two")
        }
    }
}
