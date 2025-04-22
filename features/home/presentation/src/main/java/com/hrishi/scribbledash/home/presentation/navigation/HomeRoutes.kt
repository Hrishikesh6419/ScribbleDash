package com.hrishi.scribbledash.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.hrishi.scribbledash.domain.model.common.DifficultySetting
import kotlinx.serialization.Serializable

@Serializable
data object HomeBaseRoute

@Serializable
data object HomeScreenRoute

@Serializable
data object FutureDestinationBaseRoute

@Serializable
data object FutureDestinationRoute

@Serializable
data object OneRoundWonderScreenRoute

@Serializable
data class OneRoundWonderDrawingScreenRoute(val difficultySetting: DifficultySetting)

fun NavController.navigateToHomeScreen(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(HomeScreenRoute, navOptions)

fun NavController.navigateToFutureDestination(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(FutureDestinationRoute, navOptions)

fun NavController.navigateToOneRoundWonderScreenRoute(
    navOptions: NavOptionsBuilder.() -> Unit = {}
) = navigate(OneRoundWonderScreenRoute, navOptions)

fun NavController.navigateToOneRoundWonderDrawingScreenRoute(
    difficultySetting: DifficultySetting,
    navOptions: NavOptionsBuilder.() -> Unit = {}
) = navigate(OneRoundWonderDrawingScreenRoute(difficultySetting), navOptions)