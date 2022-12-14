package com.example.moviews

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.moviews.login.SignUpScreen
import com.example.moviews.model.MovieResponse
import com.example.moviews.model.MovieResponseItems
import com.example.moviews.navigation.BottomBarScreen
import com.example.moviews.view.*

@Composable
fun BottomNavGraph(
    navController: NavHostController,
){
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
    ){
        composable(route = BottomBarScreen.Home.route){
            MovieListScreen(navController = navController)
        }
        composable(
            route = BottomBarScreen.MyList.route,
            arguments = listOf(
            //mylist_screen/{MymovieTitle}/{Myvote_average}/{Myposter_path}
            navArgument("MymovieTitle"){
                type = NavType.StringType
            },
            navArgument("Myvote_average"){
                type = NavType.FloatType
            },
            //navArgument("Myposter_path"){
            //    type = NavType.StringType
           // }
        )
        ){
            val MymovieTitle = remember {
                it.arguments?.getString("MymovieTitle")
            }
            val Myvote_average = remember {
                it.arguments?.getFloat("Myvote_average")
            }
           // val Myposter_path = remember {
          //      it.arguments?.getString("Myposter_path")
          //  }
            MyListScreen()
        }
        //UPCOMING SCREEN
        composable(route = BottomBarScreen.UpComing.route){
            UpComingScreen(navController = navController)
        }

        composable("login_screen"){
            LoginScreen(navController = navController, hintUser="UserName",hintPassword="password")
        }
        composable("signup_screen"){
            SignUpScreen(navController = navController, hintName = "Name", hintUser="Email",hintPassword="Password")
        }
        composable("bottom_bar"){
            BottomNavigation()
        }

        //DETAIL SCREEN
        composable(
            //"movie_detail_screen/{movieTitle}/{popularity}",
            "movie_detail_screen/{movieTitle}/{popularity}/{release_date}/{vote_average}/{overview}/{poster_path}",
            arguments = listOf(
                navArgument("movieTitle"){
                    type = NavType.StringType
                },
                navArgument("popularity"){
                    type = NavType.FloatType
                },
                navArgument("release_date"){
                    type = NavType.StringType
                },
                navArgument("vote_average"){
                    type = NavType.FloatType
                },
                navArgument("overview"){
                    type = NavType.StringType
                },
                navArgument("poster_path"){
                    type = NavType.StringType
                }
            )
        ){
            val movieTitle = remember {
                it.arguments?.getString("movieTitle")
            }
            val popularity = remember {
                it.arguments?.getFloat("popularity")
            }
            val release_date = remember {
                it.arguments?.getString("release_date")
            }
            val vote_average = remember {
                it.arguments?.getFloat("vote_average")
            }
            val overview = remember {
                it.arguments?.getString("overview")
            }
            val poster_path = remember {
                it.arguments?.getString("poster_path")
            }

            MovieDetailScreen(
                orginal_title = movieTitle ?: "",
                popularity = popularity!!.toDouble(),
                release_date = release_date ?: "",
                vote_average = vote_average!!.toDouble(),
                overview = overview ?: "",
                poster_path=poster_path ?:"",
                navController = navController)
        }
    }

}