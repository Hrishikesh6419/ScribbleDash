package com.scribbledash.statistics.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hrishi.ui.animation.NavigationAnimations
import com.scribbledash.statistics.presentation.home.StatisticsScreenRoot

fun NavGraphBuilder.statisticsNavGraph() {
    navigation<StatisticsBaseRoute>(
        startDestination = StatisticsHomeRoute
    ) {
        composable<StatisticsHomeRoute>(
            enterTransition = NavigationAnimations.enterFromRight,
            exitTransition = NavigationAnimations.exitToRight,
            popEnterTransition = NavigationAnimations.enterFromLeft,
            popExitTransition = NavigationAnimations.exitToLeft
        ) {
            StatisticsScreenRoot()
        }
    }
}