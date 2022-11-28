package com.example.moviews

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun CrashEntegrasyonu(){
    val crashButton = Button(onClick = {
        throw RuntimeException("Test Crash")

    }) {
        Text(text = "test Crash")
    }





}