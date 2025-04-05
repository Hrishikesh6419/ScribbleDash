package com.hrishi.scribbledash.presentation.drawing

import com.hrishi.scribbledash.presentation.model.DifficultySettingUi

data class OneRoundDrawingViewState(
    val difficultySetting: DifficultySettingUi = DifficultySettingUi.BEGINNER,
    val isClearCanvasEnabled: Boolean = false,
    val isRedoEnabled: Boolean = false,
    val isUndoEnabled: Boolean = false
)