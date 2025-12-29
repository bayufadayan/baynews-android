@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.baynews

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.baynews.screens.*
import com.example.baynews.ui.home.HomeViewModel
import android.net.Uri
import androidx.compose.runtime.remember

@Composable
fun BayNewsApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navBackStackEntry?.destination?.route

    val showTopBar = route != null && route != Routes.SPLASH
    val canNavigateBack = navController.previousBackStackEntry != null &&
            route != Routes.HOME

    val title = when {
        route == Routes.HOME -> "BayNews"
        route?.startsWith(Routes.DETAIL) == true -> "Detail"
        route == Routes.PROFILE -> "Profile"
        else -> "BayNews"
    }

    Scaffold(
        topBar = {
            if (showTopBar) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = title,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        if (canNavigateBack) {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                            }
                        }
                    },
                    actions = {
                        if (route == Routes.HOME) {
                            IconButton(onClick = { navController.navigate(Routes.PROFILE) }) {
                                Icon(Icons.Filled.Person, contentDescription = "Profile")
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.SPLASH,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.SPLASH) {
                SplashScreen(
                    onFinished = {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.SPLASH) { inclusive = true }
                        }
                    }
                )
            }

            composable(Routes.HOME) {
                val homeVm: HomeViewModel = viewModel()

                HomeScreen(
                    onOpenDetail = { articleId ->
                        navController.navigate("${Routes.DETAIL}/${Uri.encode(articleId)}")
                    },
                    vm = homeVm
                )
            }

            composable(
                route = "${Routes.DETAIL}/{id}",
                arguments = listOf(navArgument("id") { type = NavType.StringType })
            ) { backStackEntry ->
                val encoded = backStackEntry.arguments?.getString("id") ?: ""
                val id = Uri.decode(encoded)

                val homeEntry = remember(backStackEntry) { navController.getBackStackEntry(Routes.HOME) }
                val homeVm: HomeViewModel = viewModel(homeEntry)

                DetailScreen(id = id, vm = homeVm)
            }

            composable(Routes.PROFILE) {
                ProfileScreen()
            }
        }
    }
}
