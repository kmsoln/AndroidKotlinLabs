package com.karim.uniassignments.ui.components.seventh

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SevenExampleOne() {
    var result by remember { mutableStateOf("Touch events will appear here") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Black background
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    var inTouch = false
                    var downPI = -1
                    var upPI = -1
                    val maxPointers = 10

                    while (true) {
                        val event = awaitPointerEvent()
                        val action = event.type
                        val pointerCount = event.changes.size
                        val builder = StringBuilder()

                        when (action) {
                            PointerEventType.Press -> {
                                inTouch = true
                                downPI = pointerCount - 1
                            }
                            PointerEventType.Release -> {
                                inTouch = false
                                upPI = pointerCount - 1
                            }
                        }

                        builder.append("down: $downPI\n")
                        builder.append("up: $upPI\n")
                        builder.append("pointerCount = $pointerCount\n")

                        for (i in 0 until maxPointers) {
                            if (i < pointerCount) {
                                val change = event.changes[i]
                                builder.append(
                                    "Index = $i, ID = ${change.id.value}, " +
                                            "X = ${change.position.x}, Y = ${change.position.y}\n"
                                )
                            } else {
                                builder.append("Index = $i, ID = , X = , Y = \n")
                            }
                        }

                        result = builder.toString()
                    }
                }
            }
    ) {
        Text(
            text = result,
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp,
            color = Color.White // White text color
        )
    }
}