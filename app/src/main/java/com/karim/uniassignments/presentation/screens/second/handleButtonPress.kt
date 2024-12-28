package com.karim.uniassignments.presentation.screens.second

import kotlin.math.sqrt

fun handleButtonPress(
    label: String,
    displayText: String,
    isNewInput: Boolean,
    onDisplayUpdate: (String) -> Unit,
    onValueOneUpdate: (Double) -> Unit,
    onValueTwoUpdate: (Double) -> Unit,
    onOperationUpdate: (String?) -> Unit,
    onNewInputUpdate: (Boolean) -> Unit,
    currentOperation: String?,
    valueOne: Double,
    valueTwo: Double,
    memoryList: List<Double>,
    onMemoryListUpdate: (List<Double>) -> Unit,
    onShowMemoryDialogUpdate: (Boolean) -> Unit
) {
    when (label) {
        "C", "CA" -> {
            onDisplayUpdate("0")
            onValueOneUpdate(0.0)
            onValueTwoUpdate(0.0)
            onOperationUpdate(null)
            onNewInputUpdate(true)
        }
        "√" -> onDisplayUpdate(sqrt(displayText.toDouble()).toString())
        "x²" -> onDisplayUpdate((displayText.toDouble() * displayText.toDouble()).toString())
        "+", "-", "*", "/" -> {
            onValueOneUpdate(displayText.toDouble())
            onOperationUpdate(label)
            onNewInputUpdate(true)
        }
        "=" -> {
            if (currentOperation != null) {
                val secondValue = displayText.toDouble()
                val result = when (currentOperation) {
                    "+" -> valueOne + secondValue
                    "-" -> valueOne - secondValue
                    "*" -> valueOne * secondValue
                    "/" -> if (secondValue != 0.0) valueOne / secondValue else Double.NaN
                    else -> displayText.toDouble()
                }
                onDisplayUpdate(result.toString())
                onValueOneUpdate(result)
                onOperationUpdate(null)
                onNewInputUpdate(true)
            }
        }
        "M" -> onMemoryListUpdate(memoryList + displayText.toDouble())
        "MC" -> onMemoryListUpdate(emptyList())
        "MR" -> onShowMemoryDialogUpdate(true)
        else -> {
            if (isNewInput) {
                onDisplayUpdate(label)
                onNewInputUpdate(false)
            } else {
                onDisplayUpdate(displayText + label)
            }
        }
    }
}