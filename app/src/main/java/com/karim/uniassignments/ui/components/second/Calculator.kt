package com.karim.uniassignments.ui.components.second

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karim.uniassignments.presentation.screens.second.handleButtonPress
import com.karim.uniassignments.ui.theme.UniAssignmentsTheme

@Composable
fun Calculator() {
    var displayText by remember { mutableStateOf("0") }
    var valueOne by remember { mutableStateOf(0.0) }
    var valueTwo by remember { mutableStateOf(0.0) }
    var operation by remember { mutableStateOf<String?>(null) }
    var isNewInput by remember { mutableStateOf(true) }
    var memoryList by remember { mutableStateOf(listOf<Double>()) }
    var showMemoryDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = displayText,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        val buttons = listOf(
            listOf("C", "CA", "√", "x²"),
            listOf("7", "8", "9", "/"),
            listOf("4", "5", "6", "*"),
            listOf("1", "2", "3", "-"),
            listOf("0", ".", "=", "+"),
            listOf("M", "MC", "MR")
        )

        buttons.forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                row.forEach { label ->
                    CalculatorButton(label) {
                        handleButtonPress(
                            label,
                            displayText,
                            isNewInput,
                            onDisplayUpdate = { displayText = it },
                            onValueOneUpdate = { valueOne = it },
                            onValueTwoUpdate = { valueTwo = it },
                            onOperationUpdate = { operation = it },
                            onNewInputUpdate = { isNewInput = it },
                            currentOperation = operation,
                            valueOne = valueOne,
                            valueTwo = valueTwo,
                            memoryList = memoryList,
                            onMemoryListUpdate = { memoryList = it },
                            onShowMemoryDialogUpdate = { showMemoryDialog = it }
                        )
                    }
                }
            }
        }

        if (showMemoryDialog) {
            MemoryListDialog(
                memoryList = memoryList,
                onMemorySelected = { selectedMemory ->
                    displayText = selectedMemory.toString()
                    isNewInput = true
                    showMemoryDialog = false
                },
                onDismiss = { showMemoryDialog = false }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun PreviewCalculator() {
    UniAssignmentsTheme {
        Calculator()
    }
}
