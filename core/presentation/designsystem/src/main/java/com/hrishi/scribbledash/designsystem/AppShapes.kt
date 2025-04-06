package com.hrishi.scribbledash.designsystem

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
data class AppShapes(
    val small: RoundedCornerShape = RoundedCornerShape(4.dp),
    val medium: RoundedCornerShape = RoundedCornerShape(8.dp),
    val large: RoundedCornerShape = RoundedCornerShape(16.dp),
    val extraLarge: RoundedCornerShape = RoundedCornerShape(20.dp),
    val extraLarge2: RoundedCornerShape = RoundedCornerShape(28.dp),
    val extraLarge3: RoundedCornerShape = RoundedCornerShape(36.dp),
    val canvas: RoundedCornerShape = RoundedCornerShape(12.dp)
)

val LocalShapes = staticCompositionLocalOf { AppShapes() }

val MaterialTheme.appShapes: AppShapes
    @Composable @ReadOnlyComposable get() = LocalShapes.current