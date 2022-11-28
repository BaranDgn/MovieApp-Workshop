package com.example.moviews.view

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun UpComingScreen(
    navController: NavController
) {
    Box(){
        val crashButton = Button(onClick = {
            throw RuntimeException("Test Crash")

        }) {
            Text(text = "test Crash")
        }
    }
    
    
}