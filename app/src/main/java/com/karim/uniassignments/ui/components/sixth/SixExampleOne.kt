package com.karim.uniassignments.ui.components.sixth

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier

@Composable
fun SixExampleOne() {
    // Using a Canvas to fill the screen with green color
    Canvas(modifier = Modifier.fillMaxSize()) {
        // Draw a green background
        drawRect(color = Color.Green, size = size)
    }
}