package com.hrishi.scribbledash.presentation.one_round_wonder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hrishi.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun OneRoundWonderScreenRoot(
    modifier: Modifier = Modifier,
    onNavigateToDrawingScreen: () -> Unit,
    viewModel: OneRoundWonderViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is OneRoundWonderEvent.NavigateToDrawingScreen -> onNavigateToDrawingScreen()
        }
    }

    OneRoundWonderScreen(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
fun OneRoundWonderScreen(
    modifier: Modifier = Modifier,
    uiState: OneRoundWonderViewState
) {

}

@Preview
@Composable
fun PreviewOneRoundWonderScreen() {
    OneRoundWonderScreen(uiState = OneRoundWonderViewState())
}