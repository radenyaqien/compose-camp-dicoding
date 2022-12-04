package com.radenyaqien.composecampdicoding.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.radenyaqien.composecampdicoding.screen.about.AboutScreen
import com.radenyaqien.composecampdicoding.screen.detail.DetailScreen
import com.radenyaqien.composecampdicoding.screen.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "home") {

        composable(route = "home") {
            HomeScreen(
                onClick = {
                    navController.navigate("detail/${it.id}")
                }, navigateToAboutUs = {
                    navController.navigate("about")
                })
        }
        composable(
            route = "detail" + "/{Id}",
            arguments = listOf(navArgument("Id") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val navArgs = navBackStackEntry.arguments?.getInt("Id")
            if (navArgs == null) {
                Text(text = "No Id Is Provided")
            } else {
                DetailScreen(id = navArgs, onBackClicked = {
                    navController.popBackStack()
                })
            }


        }

        composable(route = "about") {
            AboutScreen(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }


    }
}
