package com.scribbledash.statistics.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hrishi.scribbledash.components.ScribbleDashTopBar
import com.hrishi.scribbledash.designsystem.ScribbleDashTheme
import com.hrishi.scribbledash.designsystem.labelXLarge

@Composable
fun StatisticsScreenRoot(
    modifier: Modifier = Modifier
) {
    StatisticsScreen(modifier = modifier)
}

@Composable
private fun StatisticsScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding(),
        topBar = {
            ScribbleDashTopBar(
                title = "Statistics",
                titleStyle = MaterialTheme.typography.labelXLarge
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Statistics Destination - Coming in Milestone 2")
        }
    }
}

@Preview
@Composable
private fun PreviewStatisticsScreen() {
    ScribbleDashTheme {
        StatisticsScreen()
    }
}