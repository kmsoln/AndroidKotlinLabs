package com.karim.uniassignments.ui.components.sixth

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas

@Composable
fun SixExampleFive() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        // Fill the canvas with ARGB color
        drawRect(
            color = Color(0xFF66CCFF), // ARGB(80, 102, 204, 255)
            size = size
        )

        // Create a Paint object for text
        val paint = android.graphics.Paint().apply {
            color = android.graphics.Color.BLUE
            textSize = 30f
        }

        // Draw canvas dimensions as text
        drawContext.canvas.nativeCanvas.drawText(
            "width = ${size.width.toInt()}, height = ${size.height.toInt()}",
            100f,
            100f,
            paint
        )

        // Draw a filled rectangle
        drawRect(
            color = Color.Blue,
            topLeft = Offset(100f, 200f),
            size = Size(100f, 100f),
            style = Fill
        )

        // Draw an outlined rectangle (stroke)
        drawRect(
            color = Color.Blue,
            topLeft = Offset(250f, 200f),
            size = Size(100f, 100f),
            style = Stroke(width = 10f)
        )

        // Draw a rectangle with fill and stroke
        drawRect(
            color = Color.Blue,
            topLeft = Offset(400f, 200f),
            size = Size(100f, 100f),
            style = Fill // First, fill
        )
        drawRect(
            color = Color.Blue,
            topLeft = Offset(400f, 200f),
            size = Size(100f, 100f),
            style = Stroke(width = 10f) // Then, stroke
        )
    }
}