package com.hrishi.scribbledash.home.presentation.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hrishi.scribbledash.home.presentation.components.BottomBarTab
import com.hrishi.scribbledash.home.presentation.future.FutureScreenRoot
import com.hrishi.scribbledash.home.presentation.home.HomeScreenRoot

fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController
) {
    navigation<HomeBaseRoute>(
        startDestination = HomeScreenRoute
    ) {
        composable<HomeScreenRoute> {
            HomeScreenRoot(
                onTabSelected = { tab ->
                    handleTabNavigation(navController, tab)
                },
                onNavigateToOneRoundWonderScreen = {

                }
            )
        }

        composable<FutureDestinationRoute> {
            FutureScreenRoot(
                onTabSelected = { tab ->
                    handleTabNavigation(navController, tab)
                }
            )
        }
    }
}

private fun handleTabNavigation(
    navController: NavHostController,
    tab: BottomBarTab
) {
    when (tab) {
        BottomBarTab.HOME -> navController.navigateToHomeScreen {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        BottomBarTab.FUTURE -> navController.navigateToFutureDestination {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}