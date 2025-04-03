package com.hrishi.scribbledash.home.presentation.future

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hrishi.scribbledash.designsystem.ScribbleDashTheme
import com.hrishi.scribbledash.designsystem.backgroundGradient
import com.hrishi.scribbledash.home.presentation.components.BottomBar
import com.hrishi.scribbledash.home.presentation.components.BottomBarTab

@Composable
fun FutureScreenRoot(
    onTabSelected: (BottomBarTab) -> Unit,
    modifier: Modifier = Modifier
) {
    FutureScreen(
        onTabSelected = onTabSelected,
        modifier = modifier
    )
}

@Composable
private fun FutureScreen(
    onTabSelected: (BottomBarTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomBar(
                selectedTab = BottomBarTab.FUTURE,
                onTabSelected = onTabSelected
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = MaterialTheme.colorScheme.backgroundGradient)
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Future Destination - Coming in Milestone 2")
        }
    }
}

@Preview
@Composable
private fun PreviewFutureScreen() {
    ScribbleDashTheme {
        FutureScreen(
            onTabSelected = {}
        )
    }
}