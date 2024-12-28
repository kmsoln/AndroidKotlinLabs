package com.karim.uniassignments.ui.components.sixth

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill

@Composable
fun SixExampleThree() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        // Fill the canvas with ARGB color
        drawRect(
            color = Color(0xFF66CCFF), // ARGB(80, 102, 204, 255)
            size = size
        )

        // Set up properties for the shapes
        val strokeWidth = 10f

        // Draw a filled point (small circle to simulate a point)
        drawCircle(
            color = Color.Red,
            radius = strokeWidth / 2,
            center = androidx.compose.ui.geometry.Offset(50f, 50f),
            style = Fill
        )

        // Draw a filled line
        drawLine(
            color = Color.Red,
            start = androidx.compose.ui.geometry.Offset(100f, 100f),
            end = androidx.compose.ui.geometry.Offset(500f, 50f),
            strokeWidth = strokeWidth
        )

        // Draw a filled circle
        drawCircle(
            color = Color.Red,
            radius = 50f,
            center = androidx.compose.ui.geometry.Offset(100f, 200f),
            style = Fill
        )

        // Draw a filled rectangle using coordinates
        drawRect(
            color = Color.Red,
            topLeft = androidx.compose.ui.geometry.Offset(200f, 150f),
            size = androidx.compose.ui.geometry.Size(200f, 50f),
            style = Fill
        )

        // Draw a filled rectangle using pre-defined rect object
        drawRect(
            color = Color.Red,
            topLeft = androidx.compose.ui.geometry.Offset(250f, 300f),
            size = androidx.compose.ui.geometry.Size(100f, 200f),
            style = Fill
        )
    }
}