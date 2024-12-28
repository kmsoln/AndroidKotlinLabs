package com.karim.uniassignments.ui.components.fifth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.karim.uniassignments.model.fifth.Record
import com.karim.uniassignments.presentation.viewmodel.MainViewModel
import com.karim.uniassignments.presentation.viewmodel.MainViewModelFactory
import com.karim.uniassignments.utils.getRecordsFromCursor

@Composable
fun FormScreen(
    viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(LocalContext.current)),
    navController: NavController
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var displayText by remember { mutableStateOf("No data to display") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                viewModel.addRecord(name, email)
                name = ""
                email = ""
            }) {
                Text("Add")
            }
            Button(onClick = {
                val cursor = viewModel.getRecords()
                val records = getRecordsFromCursor(cursor)
                displayText = formatRecordsForDisplay(records)
            }) {
                Text("Read")
            }
            Button(onClick = {
                viewModel.clearRecords()
                displayText = "Data cleared"
            }) {
                Text("Clear")
            }
            Button(onClick = {
                navController.navigate("update_screen")
                displayText = "Update Data"
            }) {
                Text("Update")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(displayText, fontSize = 16.sp)
    }
}

private fun formatRecordsForDisplay(records: List<Record>): String {
    return if (records.isEmpty()) {
        "No data found"
    } else {
        records.joinToString(separator = "\n") { "ID: ${it.id}, Name: ${it.name}, Email: ${it.email}" }
    }
}