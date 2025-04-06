package com.hrishi.scribbledash.presentation.mappers

import com.hrishi.scribbledash.components.DrawingPath
import com.hrishi.scribbledash.presentation.drawing.PathData

fun PathData.toDrawingPath() = DrawingPath(
    id = id,
    path = path,
    color = color
)