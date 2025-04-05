package com.hrishi.scribbledash.presentation.drawing

sealed interface OneRoundDrawingAction {
    data object OnCloseClick : OneRoundDrawingAction
    data object OnUndoClicked : OneRoundDrawingAction
    data object OnRedoClicked : OneRoundDrawingAction
    data object OnClearCanvasClicked : OneRoundDrawingAction
}