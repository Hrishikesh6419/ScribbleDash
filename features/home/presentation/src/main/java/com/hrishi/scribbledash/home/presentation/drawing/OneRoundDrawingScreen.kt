package com.hrishi.scribbledash.home.presentation.drawing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hrishi.scribbledash.components.DrawingCanvas
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
import com.hrishi.scribbledash.home.presentation.mappers.toDrawingPath
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
        DrawingScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(horizontal = MaterialTheme.spacing.large),
            uiState = uiState,
            onAction = onAction
        )
    }
}

@Composable
private fun DrawingScreenContent(
    modifier: Modifier = Modifier,
    uiState: OneRoundDrawingViewState,
    onAction: (OneRoundDrawingAction) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge3))

        HeaderText(uiState.drawMode)

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

        DrawingSection(
            uiState = uiState,
            onAction = onAction
        )

        Spacer(modifier = Modifier.weight(1f))

        BottomSection(
            uiState = uiState,
            onAction = onAction
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
    }
}

@Composable
private fun HeaderText(drawMode: DrawMode) {
    Text(
        text = if (drawMode is DrawMode.Preview) {
            stringResource(string.ready_set)
        } else {
            stringResource(string.time_to_draw)
        },
        style = MaterialTheme.typography.displayMedium.copy(
            color = MaterialTheme.colorScheme.onBackground
        )
    )
}

@Composable
private fun DrawingSection(
    uiState: OneRoundDrawingViewState,
    onAction: (OneRoundDrawingAction) -> Unit
) {
    DrawingCanvas(
        modifier = Modifier.size(MaterialTheme.componentDimensions.canvasSize),
        paths = uiState.paths.map { it.toDrawingPath() },
        currentPath = uiState.currentPath?.toDrawingPath(),
        onDrawStart = { onAction(OneRoundDrawingAction.OnNewPathStart) },
        onDraw = { offset -> onAction(OneRoundDrawingAction.OnDraw(offset)) },
        onDrawEnd = { onAction(OneRoundDrawingAction.OnPathEnd) },
        isInteractionEnabled = uiState.drawMode !is DrawMode.Preview
    )

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

    Text(
        text = if (uiState.drawMode is DrawMode.Preview) {
            stringResource(string.example)
        } else {
            stringResource(string.your_drawing)
        },
        style = MaterialTheme.typography.labelSmall.copy(
            color = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Composable
private fun BottomSection(
    uiState: OneRoundDrawingViewState,
    onAction: (OneRoundDrawingAction) -> Unit
) {
    if (uiState.drawMode is DrawMode.Preview) {
        CountdownTimer(remainingSeconds = uiState.drawMode.remainingSecs)
    } else {
        DrawingControls(
            isUndoEnabled = uiState.isUndoEnabled,
            isRedoEnabled = uiState.isRedoEnabled,
            isClearCanvasEnabled = uiState.isClearCanvasEnabled,
            onUndo = { onAction(OneRoundDrawingAction.OnUndoClicked) },
            onRedo = { onAction(OneRoundDrawingAction.OnRedoClicked) },
            onClear = { onAction(OneRoundDrawingAction.OnClearCanvasClicked) }
        )
    }
}

@Composable
private fun CountdownTimer(remainingSeconds: Int) {
    Text(
        text = stringResource(string.seconds_left, remainingSeconds),
        style = MaterialTheme.typography.headlineMedium.copy(
            color = MaterialTheme.colorScheme.onBackground
        )
    )
}

@Composable
private fun DrawingControls(
    isUndoEnabled: Boolean,
    isRedoEnabled: Boolean,
    isClearCanvasEnabled: Boolean,
    onUndo: () -> Unit,
    onRedo: () -> Unit,
    onClear: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = MaterialTheme.spacing.smallMedium,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ScribbleActionButton(
            icon = UndoIcon,
            contentDescription = stringResource(string.undo),
            isEnabled = isUndoEnabled,
            onClick = onUndo
        )

        ScribbleActionButton(
            icon = RedoIcon,
            contentDescription = stringResource(string.redo),
            isEnabled = isRedoEnabled,
            onClick = onRedo
        )

        ScribbleDashButton(
            modifier = Modifier.widthIn(max = MaterialTheme.componentDimensions.maxButtonWidth),
            text = stringResource(string.done),
            isEnabled = isClearCanvasEnabled,
            onClick = onClear
        )
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