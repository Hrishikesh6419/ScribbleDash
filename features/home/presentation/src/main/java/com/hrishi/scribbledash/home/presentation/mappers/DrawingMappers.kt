package com.hrishi.scribbledash.home.presentation.mappers

import com.hrishi.scribbledash.components.DrawingPath
import com.hrishi.scribbledash.home.presentation.drawing.PathData

fun PathData.toDrawingPath() = DrawingPath(
    id = id,
    path = path,
    color = color
)