package com.hrishi.scribbledash.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.hrishi.scribbledash.domain.model.common.DifficultySetting
import kotlinx.serialization.Serializable

@Serializable
data object OneRoundWonderBaseRoute

@Serializable
data object OneRoundWonderScreenRoute

@Serializable
data class OneRoundWonderDrawingScreenRoute(val difficultySetting: DifficultySetting)

fun NavController.navigateToOneRoundWonderScreenRoute(
    navOptions: NavOptionsBuilder.() -> Unit = {}
) = navigate(OneRoundWonderScreenRoute, navOptions)

fun NavController.navigateToOneRoundWonderDrawingScreenRoute(
    difficultySetting: DifficultySetting,
    navOptions: NavOptionsBuilder.() -> Unit = {}
) = navigate(OneRoundWonderDrawingScreenRoute(difficultySetting), navOptions)