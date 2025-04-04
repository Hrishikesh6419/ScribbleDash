package com.hrishi.scribbledash.presentation.drawing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hrishi.scribbledash.components.ScribbleDashTopBar
import com.hrishi.scribbledash.designsystem.CloseIcon
import com.hrishi.scribbledash.designsystem.backgroundGradient
import com.hrishi.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun OneRoundWonderDrawingScreenRoot(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    viewModel: OneRoundDrawingViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            OneRoundDrawingEvent.NavigateBack -> onNavigateBack()
        }
    }

    OneRoundWonderDrawingScreen(
        modifier = modifier,
        uiState = uiState,
        onAction = viewModel::onAction
    )
}

@Composable
fun OneRoundWonderDrawingScreen(
    modifier: Modifier = Modifier,
    uiState: OneRoundDrawingViewState,
    onAction: (OneRoundDrawingAction) -> Unit
) {
    Text("Drawing Screen")
    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .background(brush = MaterialTheme.colorScheme.backgroundGradient)
            .statusBarsPadding(),
        topBar = {
            ScribbleDashTopBar(
                icon = CloseIcon,
                onIconClick = {
                    onAction(OneRoundDrawingAction.OnCloseClick)
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(uiState.difficultySetting.displayNameResId)
            )
        }
    }
}


@Preview
@Composable
private fun PreviewOneRoundDrawingScreen() {
    OneRoundWonderDrawingScreen(
        uiState = OneRoundDrawingViewState(),
        onAction = {}
    )
}