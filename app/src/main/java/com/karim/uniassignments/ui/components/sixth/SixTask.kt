package com.karim.uniassignments.ui.components.sixth

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun SixTask() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val centerX = size.width / 2
        val centerY = size.height / 2

        drawRect(
            color = Color(0xFF66CCFF), // ARGB(80, 102, 204, 255)
            size = size
        )

        // 1. Draw a Five-Pointed Star
        val starPath = Path().apply {
            moveTo(centerX, centerY - 100f) // Top point
            lineTo(centerX - 60f, centerY + 80f) // Bottom left
            lineTo(centerX + 100f, centerY - 40f) // Top right
            lineTo(centerX - 100f, centerY - 40f) // Top left
            lineTo(centerX + 60f, centerY + 80f) // Bottom right
            close()
        }
        drawPath(
            path = starPath,
            color = Color.Yellow,
            style = Fill
        )

        // 2. Draw a Pentagon
        val pentagonPath = Path().apply {
            val radius = 100f
            for (i in 0 until 5) {
                val angle = Math.toRadians((i * 72 - 90).toDouble()).toFloat()
                val x = centerX + radius * Math.cos(angle.toDouble()).toFloat()
                val y = centerY + 250f + radius * Math.sin(angle.toDouble()).toFloat()
                if (i == 0) moveTo(x, y) else lineTo(x, y)
            }
            close()
        }
        drawPath(
            path = pentagonPath,
            color = Color.Blue,
            style = Fill
        )

        // 3. Draw a Hexagon
        val hexagonPath = Path().apply {
            val radius = 100f
            for (i in 0 until 6) {
                val angle = Math.toRadians((i * 60 - 90).toDouble()).toFloat()
                val x = centerX + 250f + radius * Math.cos(angle.toDouble()).toFloat()
                val y = centerY + 250f + radius * Math.sin(angle.toDouble()).toFloat()
                if (i == 0) moveTo(x, y) else lineTo(x, y)
            }
            close()
        }
        drawPath(
            path = hexagonPath,
            color = Color.Green,
            style = Fill
        )
    }
}