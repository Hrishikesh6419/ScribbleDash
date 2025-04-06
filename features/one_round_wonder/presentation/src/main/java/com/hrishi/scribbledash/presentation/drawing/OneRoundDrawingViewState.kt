package com.hrishi.scribbledash.presentation.drawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.hrishi.scribbledash.presentation.model.DifficultySettingUi

data class PathData(
    val id: String,
    val path: List<Offset>,
    val color: Color = Color.Black
)

data class OneRoundDrawingViewState(
    val difficultySetting: DifficultySettingUi? = null,
    val currentPath: PathData? = null,
    val paths: List<PathData> = emptyList(),
    val undoablePaths: List<PathData> = emptyList(),
    val redoPaths: List<PathData> = emptyList(),
    val isUndoEnabled: Boolean = false,
    val isRedoEnabled: Boolean = false,
    val isClearCanvasEnabled: Boolean = false
)