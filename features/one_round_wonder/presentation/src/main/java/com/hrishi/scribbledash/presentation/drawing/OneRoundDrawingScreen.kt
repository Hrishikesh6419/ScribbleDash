package com.hrishi.scribbledash.presentation.drawing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hrishi.scribbledash.components.ScribbleDashTopBar
import com.hrishi.scribbledash.components.buttons.ScribbleActionButton
import com.hrishi.scribbledash.components.buttons.ScribbleDashButton
import com.hrishi.scribbledash.designsystem.CloseIcon
import com.hrishi.scribbledash.designsystem.RedoIcon
import com.hrishi.scribbledash.designsystem.ScribbleDashTheme
import com.hrishi.scribbledash.designsystem.UndoIcon
import com.hrishi.scribbledash.designsystem.backgroundGradient
import com.hrishi.scribbledash.designsystem.componentDimensions
import com.hrishi.scribbledash.designsystem.spacing
import com.hrishi.ui.ObserveAsEvents
import com.scribbledash.presentation.ui.R.string
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
    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .background(brush = MaterialTheme.colorScheme.backgroundGradient)
            .statusBarsPadding(),
        topBar = {
            ScribbleDashTopBar(
                icon = CloseIcon,
                onIconClick = { onAction(OneRoundDrawingAction.OnCloseClick) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = MaterialTheme.spacing.large),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge3))

            Text(
                text = stringResource(string.time_to_draw),
                style = MaterialTheme.typography.displayMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground
                )
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

            // TODO: Canvas placeholder - this will be replaced with actual drawing functionality later
            Box(
                modifier = Modifier
                    .size(350.dp)
                    .background(color = Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Canvas",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.smallMedium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ScribbleActionButton(
                    icon = UndoIcon,
                    contentDescription = stringResource(string.undo),
                    isEnabled = uiState.isUndoEnabled,
                    onClick = { onAction(OneRoundDrawingAction.OnUndoClicked) }
                )

                ScribbleActionButton(
                    icon = RedoIcon,
                    contentDescription = stringResource(string.redo),
                    isEnabled = uiState.isRedoEnabled,
                    onClick = { onAction(OneRoundDrawingAction.OnRedoClicked) }
                )

                Spacer(modifier = Modifier.weight(1f))

                ScribbleDashButton(
                    modifier = Modifier.widthIn(max = MaterialTheme.componentDimensions.maxButtonWidth),
                    text = stringResource(string.clear_canvas),
                    isEnabled = uiState.isClearCanvasEnabled,
                    onClick = { onAction(OneRoundDrawingAction.OnClearCanvasClicked) }
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        }
    }
}

@Preview
@Composable
private fun PreviewOneRoundDrawingScreen() {
    ScribbleDashTheme {
        OneRoundWonderDrawingScreen(
            uiState = OneRoundDrawingViewState(
                isUndoEnabled = false,
                isRedoEnabled = false,
                isClearCanvasEnabled = false
            ),
            onAction = {}
        )
    }
}