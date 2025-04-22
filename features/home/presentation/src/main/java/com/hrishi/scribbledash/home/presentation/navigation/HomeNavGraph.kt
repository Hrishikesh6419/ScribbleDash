package com.hrishi.scribbledash.home.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hrishi.scribbledash.domain.model.common.DifficultySetting
import com.hrishi.scribbledash.home.presentation.drawing.OneRoundWonderDrawingScreenRoot
import com.hrishi.scribbledash.home.presentation.home.HomeScreenRoot
import com.hrishi.scribbledash.home.presentation.left.FutureScreenRoot
import com.hrishi.scribbledash.home.presentation.one_round_wonder.OneRoundWonderScreenRoot
import com.hrishi.ui.animation.NavigationAnimations

fun NavGraphBuilder.homeNavGraph(
    onNavigateToOneRoundWonderScreen: () -> Unit,
    onNavigateToDrawingScreen: (DifficultySetting) -> Unit,
    onNavigateBack: () -> Unit
) {
    navigation<HomeBaseRoute>(
        startDestination = HomeScreenRoute
    ) {
        composable<HomeScreenRoute>(
            enterTransition = NavigationAnimations.enterFromLeft,
            exitTransition = NavigationAnimations.exitToLeft,
            popEnterTransition = NavigationAnimations.enterFromRight,
            popExitTransition = NavigationAnimations.exitToRight
        ) {
            HomeScreenRoot(
                onNavigateToOneRoundWonderScreen = onNavigateToOneRoundWonderScreen
            )
        }

        composable<OneRoundWonderScreenRoute>(
            enterTransition = NavigationAnimations.enterFromRight,
            exitTransition = NavigationAnimations.exitToLeft,
            popEnterTransition = NavigationAnimations.enterFromLeft,
            popExitTransition = NavigationAnimations.exitToRight
        ) {
            OneRoundWonderScreenRoot(
                onNavigateToDrawingScreen = onNavigateToDrawingScreen,
                onNavigateBack = onNavigateBack
            )
        }

        composable<OneRoundWonderDrawingScreenRoute>(
            enterTransition = NavigationAnimations.enterFromRight,
            exitTransition = NavigationAnimations.exitToLeft,
            popEnterTransition = NavigationAnimations.enterFromLeft,
            popExitTransition = NavigationAnimations.exitToRight
        ) {
            OneRoundWonderDrawingScreenRoot(
                onNavigateBack = onNavigateBack
            )
        }
    }
}

fun NavGraphBuilder.futureNavGraph() {
    navigation<FutureDestinationBaseRoute>(
        startDestination = FutureDestinationRoute
    ) {
        composable<FutureDestinationRoute>(
            enterTransition = NavigationAnimations.enterFromRight,
            exitTransition = NavigationAnimations.exitToRight,
            popEnterTransition = NavigationAnimations.enterFromLeft,
            popExitTransition = NavigationAnimations.exitToLeft
        ) {
            FutureScreenRoot()
        }
    }
}