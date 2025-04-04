package com.hrishi.scribbledash.presentation.drawing

sealed interface OneRoundDrawingAction {
    data object OnCloseClick : OneRoundDrawingAction
}