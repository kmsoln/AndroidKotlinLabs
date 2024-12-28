package com.karim.uniassignments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.karim.uniassignments.ui.menu.MainNavHost
import com.karim.uniassignments.ui.theme.UniAssignmentsTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UniAssignmentsTheme {
                MainNavHost()
            }
        }
    }
}

