package com.karim.uniassignments.ui.components.sixth

import androidx.compose.foundation.layout.*
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
fun Six( navController : NavController){

    val buttonList = listOf(
        MenuButton(
            label = "Пример 1",
            onClick = { navController.navigate("lab_6_1") },
        ),

        MenuButton(
            label = "Пример 2",
            onClick = { navController.navigate("lab_6_2") }
        ),


        MenuButton(
            label = "Пример 3",
            onClick = { navController.navigate("lab_6_3") }
        ),

        MenuButton(
            label = "Пример 4",
            onClick = { navController.navigate("lab_6_4") }
        ),

        MenuButton(
            label = "Пример 5",
            onClick = { navController.navigate("lab_6_5") }
        ),

        MenuButton(
            label = "Задания",
            onClick = { navController.navigate("lab_6_task") }
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

