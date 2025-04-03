package com.hrishi.scribbledash.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import kotlinx.serialization.Serializable

@Serializable
data object OneRoundWonderBaseRoute

@Serializable
data object OneRoundWonderScreenRoute

fun NavController.navigateToOneRoundWonderScreenRoute(
    navOptions: NavOptionsBuilder.() -> Unit = {}
) = navigate(OneRoundWonderScreenRoute, navOptions)