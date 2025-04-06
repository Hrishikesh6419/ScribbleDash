package com.hrishi.scribbledash.presentation.drawing

import androidx.compose.ui.geometry.Offset

sealed interface OneRoundDrawingAction {
    data object OnCloseClick : OneRoundDrawingAction
    data object OnUndoClicked : OneRoundDrawingAction
    data object OnRedoClicked : OneRoundDrawingAction
    data object OnClearCanvasClicked : OneRoundDrawingAction
    data object OnNewPathStart : OneRoundDrawingAction
    data class OnDraw(val offset: Offset) : OneRoundDrawingAction
    data object OnPathEnd : OneRoundDrawingAction
}