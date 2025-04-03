package com.hrishi.scribbledash.home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _eventChannel = Channel<HomeEvent> { }
    val events = _eventChannel.receiveAsFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            HomeAction.OnOneRoundWonderClick -> {
                viewModelScope.launch {
                    _eventChannel.send(HomeEvent.OnNavigateToOneRoundWonderScreen)
                }
            }
        }
    }
}