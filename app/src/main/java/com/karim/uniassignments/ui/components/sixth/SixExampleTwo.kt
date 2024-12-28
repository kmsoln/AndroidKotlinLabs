package com.karim.uniassignments.ui.components.sixth

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay


@Composable
fun SixExampleTwo() {
    // State to store canvas color
    var canvasColor by remember { mutableStateOf(Color.Green) }

    // Using a coroutine to simulate the drawing thread
    LaunchedEffect(Unit) {
        // Continuously update canvas color or logic
        while (true) {
            delay(1000) // Delay for demonstration purposes
            canvasColor = if (canvasColor == Color.Green) Color.Blue else Color.Green
        }
    }

    // Canvas to draw with updated logic
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(color = canvasColor, size = size)
    }
}