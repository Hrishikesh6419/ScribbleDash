package com.hrishi.scribbledash.home.presentation.drawing

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.hrishi.scribbledash.home.presentation.navigation.OneRoundWonderDrawingScreenRoute
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OneRoundDrawingViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(OneRoundDrawingViewState())
    val uiState = _uiState.asStateFlow()

    private val _eventChannel = Channel<OneRoundDrawingEvent>()
    val events = _eventChannel.receiveAsFlow()

    private val route: OneRoundWonderDrawingScreenRoute = savedStateHandle.toRoute()

    init {
        setupInitialState()
        startPreviewCountdownIfNeeded()
    }

    private fun setupInitialState() {
        _uiState.update {
            it.copy(
                difficultySetting = com.hrishi.scribbledash.home.presentation.model.DifficultySettingUi.fromDomain(
                    route.difficultySetting
                )
            )
        }
    }

    private fun startPreviewCountdownIfNeeded() {
        viewModelScope.launch {
            val drawMode = _uiState.value.drawMode
            if (drawMode is DrawMode.Preview) {
                countdownAndSwitchToDrawMode(drawMode.remainingSecs)
            }
        }
    }

    private suspend fun countdownAndSwitchToDrawMode(startSeconds: Int) {
        var remainingSeconds = startSeconds
        while (remainingSeconds > 0) {
            delay(1000)
            remainingSeconds -= 1
            updateRemainingTime(remainingSeconds)
        }
        switchToDrawMode()
    }

    private fun updateRemainingTime(remainingSeconds: Int) {
        _uiState.update {
            it.copy(
                drawMode = DrawMode.Preview(remainingSeconds)
            )
        }
    }

    private fun switchToDrawMode() {
        _uiState.update {
            it.copy(
                drawMode = DrawMode.Draw
            )
        }
    }

    fun onAction(action: OneRoundDrawingAction) {
        when (action) {
            is OneRoundDrawingAction.OnCloseClick -> navigateBack()
            is OneRoundDrawingAction.OnClearCanvasClicked -> onClearCanvas()
            is OneRoundDrawingAction.OnRedoClicked -> onRedo()
            is OneRoundDrawingAction.OnUndoClicked -> onUndo()
            is OneRoundDrawingAction.OnNewPathStart -> onNewPathStart()
            is OneRoundDrawingAction.OnDraw -> onDraw(action.offset)
            is OneRoundDrawingAction.OnPathEnd -> onPathEnd()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _eventChannel.send(OneRoundDrawingEvent.NavigateBack)
        }
    }

    private fun onNewPathStart() {
        _uiState.update { state ->
            state.copy(
                currentPath = createNewPath(),
                redoPaths = emptyList(),
                isRedoEnabled = false
            )
        }
    }

    private fun createNewPath(): PathData {
        return PathData(
            id = System.currentTimeMillis().toString(),
            path = emptyList()
        )
    }

    private fun onDraw(offset: Offset) {
        val currentPath = _uiState.value.currentPath ?: return
        _uiState.update { state ->
            state.copy(
                currentPath = currentPath.copy(
                    path = currentPath.path + offset
                )
            )
        }
    }

    private fun onPathEnd() {
        val currentPath = _uiState.value.currentPath ?: return
        _uiState.update { state ->
            val newPaths = state.paths + currentPath
            val newUndoablePaths = state.undoablePaths + currentPath

            state.copy(
                currentPath = null,
                paths = newPaths,
                undoablePaths = newUndoablePaths,
                redoPaths = emptyList(),
                isUndoEnabled = true,
                isRedoEnabled = false,
                isClearCanvasEnabled = true
            )
        }
    }

    private fun onUndo() {
        _uiState.update { state ->
            if (state.undoablePaths.isEmpty()) return@update state

            val pathToUndo = state.undoablePaths.last()
            val newUndoablePaths = state.undoablePaths.dropLast(1)
            val newPaths = state.paths.filter { it.id != pathToUndo.id }
            val newRedoPaths = (state.redoPaths + pathToUndo).takeLast(MAX_REDO_PATHS)

            state.copy(
                paths = newPaths,
                undoablePaths = newUndoablePaths,
                redoPaths = newRedoPaths,
                isUndoEnabled = newUndoablePaths.isNotEmpty(),
                isRedoEnabled = true,
                isClearCanvasEnabled = newPaths.isNotEmpty()
            )
        }
    }

    private fun onRedo() {
        _uiState.update { state ->
            if (state.redoPaths.isEmpty()) return@update state

            val pathToRedo = state.redoPaths.last()
            val newRedoPaths = state.redoPaths.dropLast(1)
            val newPaths = state.paths + pathToRedo
            val newUndoablePaths = state.undoablePaths + pathToRedo

            state.copy(
                paths = newPaths,
                undoablePaths = newUndoablePaths,
                redoPaths = newRedoPaths,
                isUndoEnabled = true,
                isRedoEnabled = newRedoPaths.isNotEmpty(),
                isClearCanvasEnabled = true
            )
        }
    }

    private fun onClearCanvas() {
        _uiState.update { state ->
            state.copy(
                paths = emptyList(),
                undoablePaths = emptyList(),
                redoPaths = emptyList(),
                isUndoEnabled = false,
                isRedoEnabled = false,
                isClearCanvasEnabled = false
            )
        }
    }

    companion object {
        private const val MAX_REDO_PATHS = 5
    }
}