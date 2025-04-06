package com.hrishi.scribbledash.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.util.fastForEach
import com.hrishi.scribbledash.designsystem.ScribbleDashTheme
import com.hrishi.scribbledash.designsystem.appShapes
import com.hrishi.scribbledash.designsystem.componentDimensions
import com.hrishi.scribbledash.designsystem.spacing
import kotlin.math.abs

data class DrawingPath(
    val id: String,
    val path: List<Offset>,
    val color: Color = Color.Black
)

@Composable
fun DrawingCanvas(
    modifier: Modifier = Modifier,
    paths: List<DrawingPath> = emptyList(),
    currentPath: DrawingPath? = null,
    showGrid: Boolean = true,
    gridColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceContainerHigh,
    strokeWidth: Dp = MaterialTheme.componentDimensions.drawingStokeWidth,
    gridLineWidth: Dp = MaterialTheme.componentDimensions.borderWidth,
    cornerShape: RoundedCornerShape = MaterialTheme.appShapes.extraLarge2,
    innerPadding: Dp = MaterialTheme.spacing.smallMedium,
    onDrawStart: () -> Unit = {},
    onDraw: (Offset) -> Unit = {},
    onDrawEnd: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .shadow(
                elevation = MaterialTheme.componentDimensions.smallShadow,
                shape = cornerShape
            )
            .clip(cornerShape)
            .background(backgroundColor)
            .padding(innerPadding)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(MaterialTheme.appShapes.medium)
                .border(
                    width = gridLineWidth,
                    color = gridColor,
                    shape = RoundedCornerShape(MaterialTheme.spacing.extraLarge)
                )
                .background(backgroundColor)
        ) {
            Canvas(
                modifier = Modifier
                    .matchParentSize()
                    .clip(MaterialTheme.appShapes.medium)
                    .pointerInput(true) {
                        detectDragGestures(
                            onDragStart = { onDrawStart() },
                            onDragEnd = { onDrawEnd() },
                            onDrag = { change, _ -> onDraw(change.position) },
                            onDragCancel = { onDrawEnd() }
                        )
                    }
            ) {
                if (showGrid) {
                    drawGridLines(gridColor, gridLineWidth)
                }

                paths.fastForEach { drawingPath ->
                    drawPath(
                        path = drawingPath.path,
                        color = drawingPath.color,
                        thickness = strokeWidth
                    )
                }

                currentPath?.let {
                    drawPath(
                        path = it.path,
                        color = it.color,
                        thickness = strokeWidth
                    )
                }
            }
        }
    }
}

private fun DrawScope.drawGridLines(lineColor: Color, lineWidth: Dp) {
    val canvasWidth = size.width
    val canvasHeight = size.height

    val cellWidth = canvasWidth / 3
    val cellHeight = canvasHeight / 3

    for (i in 1..2) {
        val x = cellWidth * i
        drawLine(
            color = lineColor,
            start = Offset(x, 0f),
            end = Offset(x, canvasHeight),
            strokeWidth = lineWidth.toPx()
        )
    }

    for (i in 1..2) {
        val y = cellHeight * i
        drawLine(
            color = lineColor,
            start = Offset(0f, y),
            end = Offset(canvasWidth, y),
            strokeWidth = lineWidth.toPx()
        )
    }
}

private fun DrawScope.drawPath(
    path: List<Offset>,
    color: Color,
    thickness: Dp
) {
    val smoothedPath = Path().apply {
        if (path.isNotEmpty()) {
            moveTo(path.first().x, path.first().y)

            val smoothness = 5
            for (i in 1..path.lastIndex) {
                val from = path[i - 1]
                val to = path[i]
                val dx = abs(from.x - to.x)
                val dy = abs(from.y - to.y)
                if (dx >= smoothness || dy >= smoothness) {
                    quadraticTo(
                        x1 = (from.x + to.x) / 2f,
                        y1 = (from.y + to.y) / 2f,
                        x2 = to.x,
                        y2 = to.y
                    )
                }
            }
        }
    }

    drawPath(
        path = smoothedPath,
        color = color,
        style = Stroke(
            width = thickness.toPx(),
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
    )
}

@Preview
@Composable
fun DrawingCanvasPreview() {
    ScribbleDashTheme {
        DrawingCanvas()
    }
}