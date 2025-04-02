package com.hrishi.scribbledash.home.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hrishi.scribbledash.home.presentation.home.HomeScreenRoot

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    navigation<HomeBaseRoute>(
        startDestination = HomeScreenRoute
    ) {
        composable<HomeScreenRoute> {
            HomeScreenRoot()
        }
    }
}