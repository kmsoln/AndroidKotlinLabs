package com.karim.uniassignments.ui.components.seventh

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.changedToDownIgnoreConsumed
import androidx.compose.ui.input.pointer.changedToUpIgnoreConsumed
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun SevenTask() {
    // State to manage drawn paths
    val paths = remember { mutableStateListOf<Path>() }
    val currentPath = remember { mutableStateOf<Path?>(null) }

    // State to manage zoom level
    var scale by remember { mutableStateOf(1f) }
    var lastDistance by remember { mutableStateOf(0f) }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()

                        when (event.changes.size) {
                            1 -> {
                                // Single touch: freehand drawing
                                val change = event.changes.first()
                                handleDrawing(change, paths, currentPath)
                            }
                            2 -> {
                                // Two fingers: pinch-to-zoom
                                val positions = event.changes.map { it.position }
                                val distance = calculateDistance(positions[0], positions[1])

                                if (lastDistance != 0f) {
                                    scale *= distance / lastDistance
                                }
                                lastDistance = distance
                            }
                            else -> {
                                lastDistance = 0f
                            }
                        }
                    }
                }
            }
    ) {
        // Scale the canvas
        scale(scale) {
            // Draw background
            drawRect(
                color = Color(0xFF66CCFF),
                size = size
            )

            val centerX = size.width / 2
            val centerY = size.height / 2

            // Draw shapes
            drawStar(centerX, centerY - 200f, 100f, Color.Yellow)
            drawPentagon(centerX, centerY + 100f, 100f, Color.Blue)
            drawHexagon(centerX + 300f, centerY + 100f, 100f, Color.Green)
        }

        // Draw all paths
        paths.forEach { path ->
            drawPath(
                path = path,
                color = Color.Black,
                style = Stroke(width = 5f)
            )
        }
    }
}

// Helper to handle drawing paths
private fun handleDrawing(
    change: androidx.compose.ui.input.pointer.PointerInputChange,
    paths: MutableList<Path>,
    currentPath: MutableState<Path?>
) {
    if (change.changedToDownIgnoreConsumed()) {
        // Start a new path
        val newPath = Path().apply { moveTo(change.position.x, change.position.y) }
        currentPath.value = newPath
        paths.add(newPath)
    } else if (change.changedToUpIgnoreConsumed()) {
        // Finish the current path
        currentPath.value = null
    } else if (currentPath.value != null) {
        // Continue drawing the current path
        currentPath.value?.lineTo(change.position.x, change.position.y)
    }
}

// Helper to draw a star
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawStar(
    centerX: Float,
    centerY: Float,
    radius: Float,
    color: Color
) {
    val path = Path().apply {
        moveTo(centerX, centerY - radius) // Top point
        lineTo(centerX - radius * 0.6f, centerY + radius * 0.8f) // Bottom left
        lineTo(centerX + radius * 1.0f, centerY - radius * 0.4f) // Top right
        lineTo(centerX - radius * 1.0f, centerY - radius * 0.4f) // Top left
        lineTo(centerX + radius * 0.6f, centerY + radius * 0.8f) // Bottom right
        close()
    }
    drawPath(path, color, style = Fill)
}

// Helper to draw a pentagon
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawPentagon(
    centerX: Float,
    centerY: Float,
    radius: Float,
    color: Color
) {
    val path = Path().apply {
        for (i in 0 until 5) {
            val angle = Math.toRadians((i * 72 - 90).toDouble()).toFloat()
            val x = centerX + radius * Math.cos(angle.toDouble()).toFloat()
            val y = centerY + radius * Math.sin(angle.toDouble()).toFloat()
            if (i == 0) moveTo(x, y) else lineTo(x, y)
        }
        close()
    }
    drawPath(path, color, style = Fill)
}

// Helper to draw a hexagon
private fun androidx.compose.ui.graphics.drawscope.DrawScope.drawHexagon(
    centerX: Float,
    centerY: Float,
    radius: Float,
    color: Color
) {
    val path = Path().apply {
        for (i in 0 until 6) {
            val angle = Math.toRadians((i * 60 - 90).toDouble()).toFloat()
            val x = centerX + radius * Math.cos(angle.toDouble()).toFloat()
            val y = centerY + radius * Math.sin(angle.toDouble()).toFloat()
            if (i == 0) moveTo(x, y) else lineTo(x, y)
        }
        close()
    }
    drawPath(path, color, style = Fill)
}

// Helper to calculate distance between two points
private fun calculateDistance(p1: Offset, p2: Offset): Float {
    return sqrt((p2.x - p1.x).pow(2) + (p2.y - p1.y).pow(2))
}