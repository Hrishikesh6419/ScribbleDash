package com.hrishi.scribbledash.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hrishi.scribbledash.presentation.drawing.OneRoundWonderDrawingScreenRoot
import com.hrishi.scribbledash.presentation.one_round_wonder.OneRoundWonderScreenRoot
import com.hrishi.ui.animation.NavigationAnimations

fun NavGraphBuilder.oneRoundWonderNavGraph(
    navController: NavHostController,
) {
    navigation<OneRoundWonderBaseRoute>(
        startDestination = OneRoundWonderScreenRoute
    ) {
        composable<OneRoundWonderScreenRoute>(
            enterTransition = NavigationAnimations.enterFromRight,
            exitTransition = NavigationAnimations.exitToLeft,
            popEnterTransition = NavigationAnimations.enterFromLeft,
            popExitTransition = NavigationAnimations.exitToRight
        ) {
            OneRoundWonderScreenRoot(
                onNavigateToDrawingScreen = {
                    navController.navigateToOneRoundWonderDrawingScreenRoute(it)
                },
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<OneRoundWonderDrawingScreenRoute>(
            enterTransition = NavigationAnimations.enterFromRight,
            exitTransition = NavigationAnimations.exitToLeft,
            popEnterTransition = NavigationAnimations.enterFromLeft,
            popExitTransition = NavigationAnimations.exitToRight
        ) {
            OneRoundWonderDrawingScreenRoot(
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}