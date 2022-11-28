package com.example.moviews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviews.login.SignUpScreen
import com.example.moviews.navigation.BottomBarScreen
import com.example.moviews.ui.theme.MovieWsTheme
import com.example.moviews.view.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.prefs.Preferences

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MovieWsTheme {
                val navController = rememberNavController()

                NavHost(navController = navController,
                    startDestination = "splash_screen"
                ){

                    composable(
                        "bottom_bar"){
                        BottomNavigation()
                    }


                    composable(
                        "signup_screen"){
                        SignUpScreen(navController, hintName = "Name", hintUser = "Email", hintPassword = "Password")
                    }

                    composable("login_screen"){
                        LoginScreen(navController = navController, hintUser="Email",hintPassword="Password")
                    }

                    composable(
                        "splash_screen"){
                        SplashScreen(navController)
                    }
                }
            }
        }
    }
}

