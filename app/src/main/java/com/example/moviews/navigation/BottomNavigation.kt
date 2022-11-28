package com.example.moviews.view


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.moviews.BottomNavGraph
import com.example.moviews.navigation.BottomBarScreen



@Composable
fun BottomNavigation(){
val navController = rememberNavController()
   Scaffold(
       bottomBar = {
           ButtomNavBar( navController = navController)}
   ){
       BottomNavGraph(navController= navController)
   }

}
@Composable
fun ButtomNavBar(navController: NavHostController){
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.MyList,
        BottomBarScreen.UpComing
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        screens.forEach {screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
){

    BottomNavigationItem(
        label = {
                Text(text = screen.title)
        },
        icon = {
               Icon(
                   imageVector = screen.icon,
                   contentDescription = "navigation Icon")
        },
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )

}



 