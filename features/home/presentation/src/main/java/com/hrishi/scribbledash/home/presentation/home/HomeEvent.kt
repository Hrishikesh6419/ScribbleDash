package com.hrishi.scribbledash.home.presentation.home

sealed interface HomeEvent {
    data object OnNavigateToOneRoundWonderScreen : HomeEvent
}