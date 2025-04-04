package com.hrishi.scribbledash.presentation.one_round_wonder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OneRoundWonderViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(OneRoundWonderViewState())
    val uiState = _uiState.asStateFlow()

    private val _eventChannel = Channel<OneRoundWonderEvent>()
    val events = _eventChannel.receiveAsFlow()

    fun onAction(action: OneRoundWonderAction) {
        when (action) {
            is OneRoundWonderAction.OnDifficultOptionClicked -> {
                viewModelScope.launch {
                    _eventChannel.send(
                        OneRoundWonderEvent.NavigateToDrawingScreen(
                            action.difficultySetting.toDomain()
                        )
                    )
                }
            }

            OneRoundWonderAction.OnCloseClick -> {
                viewModelScope.launch {
                    _eventChannel.send(
                        OneRoundWonderEvent.NavigateBack
                    )
                }
            }
        }
    }
}