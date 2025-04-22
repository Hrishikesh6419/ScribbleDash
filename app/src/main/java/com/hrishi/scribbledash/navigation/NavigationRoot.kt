package com.hrishi.scribbledash.navigation

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hrishi.scribbledash.home.presentation.components.BottomBar
import com.hrishi.scribbledash.home.presentation.components.BottomBarTab
import com.hrishi.scribbledash.home.presentation.navigation.FutureDestinationRoute
import com.hrishi.scribbledash.home.presentation.navigation.HomeBaseRoute
import com.hrishi.scribbledash.home.presentation.navigation.HomeScreenRoute
import com.hrishi.scribbledash.home.presentation.navigation.futureNavGraph
import com.hrishi.scribbledash.home.presentation.navigation.homeNavGraph
import com.hrishi.scribbledash.home.presentation.navigation.navigateToFutureDestination
import com.hrishi.scribbledash.home.presentation.navigation.navigateToHomeScreen
import com.hrishi.scribbledash.home.presentation.navigation.navigateToOneRoundWonderDrawingScreenRoute
import com.hrishi.scribbledash.home.presentation.navigation.navigateToOneRoundWonderScreenRoute

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val topLevelDestinations = setOf(
        HomeScreenRoute::class,
        FutureDestinationRoute::class,
    )

    val isInTopLevelDestination = currentDestination?.hierarchy?.any { destination ->
        topLevelDestinations.any { destination.hasRoute(it) }
    } ?: false

    val selectedTab = when {
        currentDestination?.hierarchy?.any { it.hasRoute(HomeScreenRoute::class) } == true -> BottomBarTab.HOME
        currentDestination?.hierarchy?.any { it.hasRoute(FutureDestinationRoute::class) } == true -> BottomBarTab.FUTURE
        else -> BottomBarTab.HOME
    }

    Scaffold(
        bottomBar = {
            if (isInTopLevelDestination) {
                BottomBar(
                    selectedTab = selectedTab,
                    onTabSelected = { tab ->
                        handleTabNavigation(navController, tab)
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = HomeBaseRoute,
            modifier = Modifier
                .padding(paddingValues)
                .consumeWindowInsets(paddingValues)
        ) {
            homeNavGraph(
                onNavigateToOneRoundWonderScreen = {
                    navController.navigateToOneRoundWonderScreenRoute()
                },
                onNavigateToDrawingScreen = {
                    navController.navigateToOneRoundWonderDrawingScreenRoute(it)
                },
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
            futureNavGraph()
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