package com.hrishi.scribbledash.presentation.drawing

sealed interface OneRoundDrawingEvent {
    data object NavigateBack : OneRoundDrawingEvent
}