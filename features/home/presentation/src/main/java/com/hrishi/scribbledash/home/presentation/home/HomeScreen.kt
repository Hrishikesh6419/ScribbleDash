package com.hrishi.scribbledash.home.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.hrishi.scribbledash.components.ScribbleDashTopBar
import com.hrishi.scribbledash.designsystem.OneRoundWonderIcon
import com.hrishi.scribbledash.designsystem.ScribbleDashTheme
import com.hrishi.scribbledash.designsystem.appShapes
import com.hrishi.scribbledash.designsystem.backgroundGradient
import com.hrishi.scribbledash.designsystem.spacing
import com.hrishi.scribbledash.designsystem.success
import com.hrishi.scribbledash.home.presentation.components.BottomBar
import com.hrishi.scribbledash.home.presentation.components.BottomBarTab
import com.hrishi.ui.ObserveAsEvents
import com.scribbledash.home.core.R
import com.scribbledash.presentation.ui.R.*
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
        modifier = modifier,
        onTabSelected = onTabSelected,
        onAction = viewModel::onAction
    )
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    onTabSelected: (BottomBarTab) -> Unit,
    onAction: (HomeAction) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { ScribbleDashTopBar(title = stringResource(R.string.scribbledash)) },
        bottomBar = {
            BottomBar(
                selectedTab = BottomBarTab.HOME,
                onTabSelected = onTabSelected
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = MaterialTheme.colorScheme.backgroundGradient)
                .padding(paddingValues)
                .padding(horizontal = MaterialTheme.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge5))

            Text(
                text = stringResource(string.start_drawing),
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            Text(
                text = stringResource(R.string.select_game_mode),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))

            GameModeItem(
                title = stringResource(R.string.one_round_wonder),
                icon = OneRoundWonderIcon,
                onClick = { onAction(HomeAction.OnOneRoundWonderClick) }
            )
        }
    }
}

@Composable
private fun GameModeItem(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(
                color = MaterialTheme.colorScheme.success,
                shape = MaterialTheme.appShapes.extraLarge
            )
            .padding(MaterialTheme.spacing.small)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    shape = MaterialTheme.appShapes.large
                )
                .padding(
                    start = MaterialTheme.spacing.mediumLarge
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            Icon(
                tint = Color.Unspecified,
                imageVector = icon,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    ScribbleDashTheme {
        HomeScreen(
            onTabSelected = {},
            onAction = {}
        )
    }
}