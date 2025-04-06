package com.hrishi.scribbledash.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class ComponentDimensions(
    val actionButtonSize: Dp = 60.dp,
    val buttonMinHeight: Dp = 52.dp,
    val buttonBorderPadding: Dp = 6.dp,

    val borderWidth: Dp = 1.dp,
    val iconSize: Dp = 32.dp,
    val smallShadow: Dp = 2.dp,
    val difficultIconSize: Dp = 88.dp,
    val bottomBarHeight: Dp = 88.dp,
    val gameModeTileHeight: Dp = 80.dp,
    val maxButtonWidth: Dp = 200.dp,
    val canvasSize: Dp = 350.dp,
)

val LocalComponentDimensions = staticCompositionLocalOf { ComponentDimensions() }

val MaterialTheme.componentDimensions: ComponentDimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalComponentDimensions.current