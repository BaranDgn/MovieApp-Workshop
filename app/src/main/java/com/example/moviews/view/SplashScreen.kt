package com.example.moviews.view

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviews.R
import kotlinx.coroutines.delay
import java.lang.reflect.Modifier

@Composable
fun SplashScreen(
    navController : NavController
){
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate("login_screen")
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
            .background(color = MaterialTheme.colors.secondary)
    ){
        Image(painter = painterResource(id = R.drawable.movie_icon),
            contentDescription = "Logo",
            modifier = androidx.compose.ui.Modifier.scale(scale.value).height(450.dp).width(420 .dp)

        )

    }

}