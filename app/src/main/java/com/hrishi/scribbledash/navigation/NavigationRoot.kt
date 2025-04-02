package com.hrishi.scribbledash.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.hrishi.scribbledash.home.presentation.navigation.HomeBaseRoute
import com.hrishi.scribbledash.home.presentation.navigation.homeNavGraph

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = HomeBaseRoute
    ) {
        homeNavGraph(navController = navController)
    }
}