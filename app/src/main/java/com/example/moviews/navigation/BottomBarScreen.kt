package com.example.moviews.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route : String,
    val title : String,
    val icon : ImageVector
){
    object Home : BottomBarScreen(
        route = "movie_list_screen",
        title = "Movies",
        icon = Icons.Default.Home
    )

    object MyList : BottomBarScreen(
        route = "mylist_screen/{MymovieTitle}/{Myvote_average}",
        title = "My List",
        icon = Icons.Default.Notifications
    )
    object UpComing : BottomBarScreen(
        route = "upcoming_screen",
        title = "Up Coming",
        icon = Icons.Default.Notifications
    )
}
