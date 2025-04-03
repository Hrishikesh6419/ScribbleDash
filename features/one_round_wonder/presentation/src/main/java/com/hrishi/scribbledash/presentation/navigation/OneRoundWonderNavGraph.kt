package com.hrishi.scribbledash.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.hrishi.scribbledash.presentation.one_round_wonder.OneRoundWonderScreenRoot

fun NavGraphBuilder.oneRoundWonderNavGraph(
    navController: NavHostController,
) {
    navigation<OneRoundWonderBaseRoute>(
        startDestination = OneRoundWonderScreenRoute
    ) {
        composable<OneRoundWonderScreenRoute> {
            OneRoundWonderScreenRoot()
        }
    }
}