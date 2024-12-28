package com.karim.uniassignments.ui.components.seventh

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.karim.uniassignments.ui.menu.MenuButton

@Composable
fun Seven (navController : NavController) {

    val buttonList = listOf(
        MenuButton(
            label = "Пример 1",
            onClick = { navController.navigate("lab_7_1") },
        ),

        MenuButton(
            label = "Задания",
            onClick = { navController.navigate("lab_7_task") }
        ),


    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Main Menu",
            modifier = Modifier
                .padding(bottom = 16.dp)
        )

        buttonList.forEach { button ->
            Button(
                onClick = button.onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = button.label,
                    color = Color.White,
                )
            }
        }
    }
}