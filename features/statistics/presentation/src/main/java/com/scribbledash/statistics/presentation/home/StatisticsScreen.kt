package com.scribbledash.statistics.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hrishi.scribbledash.components.ScribbleDashTopBar
import com.hrishi.scribbledash.components.StatisticsRow
import com.hrishi.scribbledash.designsystem.BoltIcon
import com.hrishi.scribbledash.designsystem.HourGlassIcon
import com.hrishi.scribbledash.designsystem.ScribbleDashTheme
import com.hrishi.scribbledash.designsystem.labelXLarge
import com.hrishi.scribbledash.designsystem.spacing
import com.scribbledash.statistics.presentation.R

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
                title = stringResource(R.string.statistics),
                titleStyle = MaterialTheme.typography.labelXLarge
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.mediumLarge))

            StatisticsRow(
                image = HourGlassIcon,
                text = stringResource(R.string.nothing_to_track_for_now),
                value = "0%"
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.smallMedium))

            StatisticsRow(
                image = BoltIcon,
                text = stringResource(R.string.nothing_to_track_for_now),
                value = "0"
            )
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