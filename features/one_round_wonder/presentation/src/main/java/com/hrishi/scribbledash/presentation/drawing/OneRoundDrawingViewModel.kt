package com.hrishi.scribbledash.presentation.drawing

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
            OneRoundDrawingAction.OnCloseClick -> {
                viewModelScope.launch {
                    _eventChannel.send(OneRoundDrawingEvent.NavigateBack)
                }
            }
        }
    }
}