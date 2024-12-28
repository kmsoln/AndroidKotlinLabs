package com.karim.uniassignments.ui.menu

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.karim.uniassignments.ui.components.eighth.Eight
import com.karim.uniassignments.ui.components.eighth.EightExampleOne
import com.karim.uniassignments.ui.components.eighth.EightTask
import com.karim.uniassignments.ui.components.fifth.FormScreen
import com.karim.uniassignments.ui.components.fifth.UpdateScreen
import com.karim.uniassignments.ui.components.first.MyName
import com.karim.uniassignments.ui.components.fourth.compass.CompassScreen
import com.karim.uniassignments.ui.components.fourth.sensor.SensorScreen
import com.karim.uniassignments.ui.components.second.Calculator
import com.karim.uniassignments.ui.components.seventh.Seven
import com.karim.uniassignments.ui.components.seventh.SevenExampleOne
import com.karim.uniassignments.ui.components.seventh.SevenTask
import com.karim.uniassignments.ui.components.sixth.Six
import com.karim.uniassignments.ui.components.sixth.SixExampleFive
import com.karim.uniassignments.ui.components.sixth.SixExampleFour
import com.karim.uniassignments.ui.components.sixth.SixExampleOne
import com.karim.uniassignments.ui.components.sixth.SixExampleThree
import com.karim.uniassignments.ui.components.sixth.SixExampleTwo
import com.karim.uniassignments.ui.components.sixth.SixTask
import com.karim.uniassignments.ui.components.third.ActivityOneScreen
import com.karim.uniassignments.ui.components.third.ActivityTwoScreen
import com.karim.uniassignments.ui.components.third.ActivityThreeScreen

@Composable
fun MainNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main_menu"
    ) {
        composable("main_menu") { MainMenu(navController) }

        // Lab work 1
        composable("lab_1") { MyName() }

        // Lab work 2
        composable("lab_2") { Calculator() }

        // Lab work 3
        composable("lab_3/{name}") { backStackEntry ->
            val message = backStackEntry.arguments?.getString("name") ?: "No name"
            ActivityOneScreen(navController, message) }
        composable("activityTwo/{message}") { backStackEntry ->
            val message = backStackEntry.arguments?.getString("message") ?: "No message"
            ActivityTwoScreen(navController, message)
        }
        composable("activityThree/{message}") { backStackEntry ->
            val message = backStackEntry.arguments?.getString("message") ?: "No message"
            ActivityThreeScreen(navController, message)
        }

        // Lab work 4
        composable("lab_4") { SensorScreen(navController) }
        composable("compassScreen") { CompassScreen() }

        // Lab work 5
        composable("lab_5") { FormScreen(navController = navController) }
        composable("update_screen") { UpdateScreen(navController = navController) }

        // Lab work 6
        composable("lab_6") { Six(navController = navController) }
        composable("lab_6_1") { SixExampleOne() }
        composable("lab_6_2") { SixExampleTwo() }
        composable("lab_6_3") { SixExampleThree() }
        composable("lab_6_4") { SixExampleFour() }
        composable("lab_6_5") { SixExampleFive() }
        composable("lab_6_task") { SixTask() }

        // Lab work 7
        composable("lab_7") { Seven(navController = navController) }
        composable("lab_7_1") { SevenExampleOne() }
        composable("lab_7_task") { SevenTask() }

        // Lab work 8
        composable("lab_8") { Eight(navController = navController) }
        composable("lab_8_1") { EightExampleOne() }
        composable("lab_8_task") { EightTask() }

    }
}
