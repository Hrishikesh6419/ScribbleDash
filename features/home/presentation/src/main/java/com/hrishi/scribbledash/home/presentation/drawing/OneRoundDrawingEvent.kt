package com.hrishi.scribbledash.home.presentation.drawing

sealed interface OneRoundDrawingEvent {
    data object NavigateBack : OneRoundDrawingEvent
}