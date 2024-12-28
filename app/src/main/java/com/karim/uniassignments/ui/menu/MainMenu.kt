package com.karim.uniassignments.ui.menu

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.karim.uniassignments.ui.theme.UniAssignmentsTheme

@Composable
fun MainMenu(navController: NavHostController) {

    val buttonList = listOf(
        MenuButton(
            label = "Лабораторная работа №1",
            onClick = { navController.navigate("lab_1") },
        ),

        MenuButton(
            label = "Лабораторная работа №2",
            onClick = { navController.navigate("lab_2") }
        ),

        MenuButton(
            label = "Лабораторная работа №3",
            onClick = { navController.navigate("lab_3/karim") }
        ),

        MenuButton(
            label = "Лабораторная работа №4",
            onClick = { navController.navigate("lab_4") }
        ),

        MenuButton(
            label = "Лабораторная работа №5",
            onClick = { navController.navigate("lab_5") }
        ),

        MenuButton(
            label = "Лабораторная работа №6",
            onClick = { navController.navigate("lab_6") }
        ),

        MenuButton(
            label = "Лабораторная работа №7",
            onClick = { navController.navigate("lab_7") }
        ),
        MenuButton(
            label = "Лабораторная работа №8",
            onClick = { navController.navigate("lab_8") }
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UniAssignmentsTheme {
        MainMenu(rememberNavController())
    }
}
