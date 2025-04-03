package com.hrishi.scribbledash.home.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hrishi.scribbledash.designsystem.ScribbleDashTheme
import com.hrishi.scribbledash.designsystem.backgroundGradient
import com.hrishi.scribbledash.home.presentation.components.BottomBar
import com.hrishi.scribbledash.home.presentation.components.BottomBarTab
import com.hrishi.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
    onTabSelected: (BottomBarTab) -> Unit,
    onNavigateToOneRoundWonderScreen: () -> Unit
) {

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            HomeEvent.OnNavigateToOneRoundWonderScreen -> onNavigateToOneRoundWonderScreen()
        }
    }

    HomeScreen(
        onTabSelected = onTabSelected,
        modifier = modifier
    )
}

@Composable
private fun HomeScreen(
    onTabSelected: (BottomBarTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomBar(
                selectedTab = BottomBarTab.HOME,
                onTabSelected = onTabSelected
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = MaterialTheme.colorScheme.backgroundGradient)
                .padding(paddingValues)
        ) {
            Text("Home Screen")
        }
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    ScribbleDashTheme {
        HomeScreen(
            onTabSelected = {}
        )
    }
}