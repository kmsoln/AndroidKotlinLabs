package com.karim.uniassignments.ui.components.fifth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
fun UpdateScreen(
    viewModel: MainViewModel = viewModel(factory = MainViewModelFactory(LocalContext.current)),
    navController: NavController
) {
    var selectedId by remember { mutableStateOf(-1) }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var records by remember { mutableStateOf(listOf<Record>()) }

    LaunchedEffect(Unit) {
        records = getRecordsFromCursor(viewModel.getRecords())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back Button
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // List of Records
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            items(records) { record ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "ID: ${record.id}, Name: ${record.name}",
                        fontSize = 16.sp
                    )
                    Button(onClick = {
                        selectedId = record.id
                        name = record.name
                        email = record.email
                    }) {
                        Text("Edit")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Edit Section
        if (selectedId != -1) {
            Text("Edit Record", fontSize = 20.sp)
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Button(
                onClick = {
                    viewModel.updateRecord(selectedId, name, email)
                    records = getRecordsFromCursor(viewModel.getRecords())
                    selectedId = -1
                    name = ""
                    email = ""
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Update")
            }
        }
    }
}
