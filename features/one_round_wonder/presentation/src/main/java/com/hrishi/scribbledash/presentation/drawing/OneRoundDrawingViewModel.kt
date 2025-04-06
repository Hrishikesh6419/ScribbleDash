package com.hrishi.scribbledash.presentation.drawing

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.hrishi.scribbledash.presentation.model.DifficultySettingUi
import com.hrishi.scribbledash.presentation.navigation.OneRoundWonderDrawingScreenRoute
import kotlinx.coroutines.channels.Channel
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
        _uiState.update {
            it.copy(
                difficultySetting = DifficultySettingUi.fromDomain(route.difficultySetting)
            )
        }
    }

    fun onAction(action: OneRoundDrawingAction) {
        when (action) {
            is OneRoundDrawingAction.OnCloseClick -> {
                viewModelScope.launch {
                    _eventChannel.send(OneRoundDrawingEvent.NavigateBack)
                }
            }

            is OneRoundDrawingAction.OnClearCanvasClicked -> onClearCanvas()
            is OneRoundDrawingAction.OnRedoClicked -> onRedo()
            is OneRoundDrawingAction.OnUndoClicked -> onUndo()
            is OneRoundDrawingAction.OnNewPathStart -> onNewPathStart()
            is OneRoundDrawingAction.OnDraw -> onDraw(action.offset)
            is OneRoundDrawingAction.OnPathEnd -> onPathEnd()
        }
    }

    private fun onNewPathStart() {
        _uiState.update { state ->
            state.copy(
                currentPath = PathData(
                    id = System.currentTimeMillis().toString(),
                    path = emptyList()
                ),
                redoPaths = emptyList(),
                isRedoEnabled = false
            )
        }
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

            val newUndoablePaths = (state.undoablePaths + currentPath).takeLast(5)

            state.copy(
                currentPath = null,
                paths = newPaths,
                undoablePaths = newUndoablePaths,
                redoPaths = emptyList(),
                isUndoEnabled = newUndoablePaths.isNotEmpty(),
                isRedoEnabled = false,
                isClearCanvasEnabled = newPaths.isNotEmpty()
            )
        }
    }

    private fun onUndo() {
        _uiState.update { state ->
            if (state.undoablePaths.isEmpty()) return@update state

            val pathToUndo = state.undoablePaths.last()

            val newUndoablePaths = state.undoablePaths.dropLast(1)

            val newPaths = state.paths.filter { it.id != pathToUndo.id }

            val newRedoPaths = (state.redoPaths + pathToUndo).takeLast(5)

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

            val newUndoablePaths = (state.undoablePaths + pathToRedo).takeLast(5)

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
}