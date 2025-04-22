package com.scribbledash.statistics.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import kotlinx.serialization.Serializable

@Serializable
data object StatisticsBaseRoute

@Serializable
data object StatisticsHomeRoute

fun NavController.navigateToStatisticsHomeRoute(navOptions: NavOptionsBuilder.() -> Unit = {}) =
    navigate(StatisticsHomeRoute, navOptions)