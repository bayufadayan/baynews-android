package com.example.baynews

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.baynews.screens.SplashScreen
import com.example.baynews.screens.HomeScreen
import com.example.baynews.screens.DetailScreen
import com.example.baynews.screens.ProfileScreen

@Composable
fun BayNewsApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(
                onFinished = { navController.navigate(Routes.HOME) { popUpTo(Routes.SPLASH) { inclusive = true } } }
            )
        }

        composable(Routes.HOME) {
            HomeScreen(
                onOpenDetail = { articleId ->
                    navController.navigate("${Routes.DETAIL}/$articleId")
                },
                onOpenProfile = {
                    navController.navigate(Routes.PROFILE)
                }
            )
        }

        composable(
            route = "${Routes.DETAIL}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            DetailScreen(
                id = id,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Routes.PROFILE) {
            ProfileScreen(onBack = { navController.popBackStack() })
        }
    }
}
