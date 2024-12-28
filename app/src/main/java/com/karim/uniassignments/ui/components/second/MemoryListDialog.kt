package com.karim.uniassignments.ui.components.second

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.karim.uniassignments.ui.theme.UniAssignmentsTheme

@Composable
fun MemoryListDialog(
    memoryList: List<Double>,
    onMemorySelected: (Double) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Memory List") },
        text = {
            Column {
                memoryList.forEach { memory ->
                    TextButton(onClick = { onMemorySelected(memory) }) {
                        Text(text = memory.toString(), fontSize = 20.sp)
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
internal fun PreviewMemoryListDialog() {
    val memoryList = listOf(1.0, 2.5, 3.8, 4.7, 5.0)

    UniAssignmentsTheme {
        MemoryListDialog(
            memoryList = memoryList,
            onMemorySelected = { selectedMemory ->
                println("Selected Memory: $selectedMemory")
            },
            onDismiss = {
                println("Dialog dismissed")
            }
        )
    }
}