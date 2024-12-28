package com.karim.uniassignments.ui.components.sixth

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.nativeCanvas

@Composable
fun SixExampleFour() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        // Fill the background
        drawRect(
            color = Color(0xFF66CCFF), // ARGB(80, 102, 204, 255)
            size = size
        )

        // Draw multiple red points
        val points = listOf(
            // New point above and in the middle
            Offset(125f, 75f),

            // Original points
            Offset(100f, 100f), Offset(150f, 100f),
            Offset(100f, 150f), Offset(150f, 150f)
        )
        points.forEach { point ->
            drawCircle(color = Color.Red, center = point, radius = 5f)
        }

        // Draw red grid lines
        val gridLines = listOf(
            Pair(Offset(300f, 100f), Offset(300f, 400f)), // Vertical line 1
            Pair(Offset(400f, 100f), Offset(400f, 400f)), // Vertical line 2
            Pair(Offset(200f, 200f), Offset(500f, 200f)), // Horizontal line 1
            Pair(Offset(200f, 300f), Offset(500f, 300f))  // Horizontal line 2
        )
        gridLines.forEach { (start, end) ->
            drawLine(color = Color.Red, start = start, end = end, strokeWidth = 10f)
        }

        // Draw green vertical line as reference
        drawLine(
            color = Color.Green,
            start = Offset(150f, 400f),
            end = Offset(150f, 600f),
            strokeWidth = 3f
        )

        // Draw text with different alignments
        val paint = android.graphics.Paint().apply {
            color = android.graphics.Color.BLUE
            textSize = 30f
        }
        drawContext.canvas.nativeCanvas.apply {
            // Text aligned left
            paint.textAlign = android.graphics.Paint.Align.LEFT
            drawText("text left", 150f, 500f, paint)

            // Text aligned center
            paint.textAlign = android.graphics.Paint.Align.CENTER
            drawText("text center", 150f, 525f, paint)

            // Text aligned right
            paint.textAlign = android.graphics.Paint.Align.RIGHT
            drawText("text right", 150f, 550f, paint)
        }


        // Draw a filled rounded rectangle
        val rectf = Rect(700f, 100f, 800f, 150f)
        drawRoundRect(
            color = Color.Green,
            topLeft = Offset(rectf.left, rectf.top),
            size = androidx.compose.ui.geometry.Size(200f, 100f),
            cornerRadius = CornerRadius(59f, 50f),
            style = Fill // Use Fill to fill the shape
        )

        // Draw a filled oval
        drawOval(
            color = Color.Green,
            topLeft = Offset(rectf.left, rectf.top + 150f),
            size = androidx.compose.ui.geometry.Size(200f, 100f),
            style = Fill
        )

        // Draw the green pie slice (arc)
        drawArc(
            color = Color.Green,
            startAngle = 90f,
            sweepAngle = 270f, // This makes the arc span 3/4 of a circle
            useCenter = true,  // Connects the edges of the arc to the center
            topLeft = Offset(rectf.left, rectf.top + 300f),
            size = androidx.compose.ui.geometry.Size(200f, 200f),
            style = Fill
        )


        // Draw the green pie slice (arc)
        drawArc(
            color = Color.Green,
            startAngle = 90f,
            sweepAngle = 270f, // This makes the arc span 3/4 of a circle
            useCenter = false,  // Connects the edges of the arc to the center
            topLeft = Offset(rectf.left, rectf.top + 600f),
            size = androidx.compose.ui.geometry.Size(200f, 200f),
            style = Fill
        )
    }
}