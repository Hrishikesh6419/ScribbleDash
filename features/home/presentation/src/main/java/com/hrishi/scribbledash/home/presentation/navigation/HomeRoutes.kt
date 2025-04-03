package com.hrishi.scribbledash.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import kotlinx.serialization.Serializable

@Serializable
data object HomeBaseRoute

@Serializable
data object HomeScreenRoute

@Serializable
data object FutureDestinationRoute

fun NavController.navigateToHomeScreen(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(HomeScreenRoute, navOptions)

fun NavController.navigateToFutureDestination(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(FutureDestinationRoute, navOptions)