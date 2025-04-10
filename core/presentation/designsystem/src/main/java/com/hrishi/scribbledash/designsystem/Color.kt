package com.hrishi.scribbledash.designsystem

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF238CFF)
val OnPrimary = Color(0xFFFFFFFF)
val Secondary = Color(0xFFAB5CFA)
val TertiaryContainer = Color(0xFFFA852C)
val Error = Color(0xFFEF1242)
val Background = Color(0xFFFEFAF6)
val SurfaceHigh = Color(0xFFFFFFFF)
val SurfaceLow = Color(0xFFEEE7E0)
val SurfaceLowest = Color(0xFFE1D5CA)
val OnBackground = Color(0xFF514437)
val OnSurface = Color(0xFFA5978A)
val OnSurfaceVariant = Color(0xFFF6F1EC)

object ExtendedColors {
    val Success = Color(0xFF0DD280)
    val OnBackgroundVariant = Color(0xFF7F7163)
    val BackgroundVariantStart = Color(0xFFFEFAF6)
    val BackgroundVariantEnd = Color(0xFFFFF1E2)
}

val ColorScheme.success: Color
    get() = ExtendedColors.Success

val ColorScheme.onBackgroundVariant: Color
    get() = ExtendedColors.OnBackgroundVariant

val ColorScheme.backgroundGradient: Brush
    get() = Brush.linearGradient(
        colors = listOf(
            ExtendedColors.BackgroundVariantStart,
            ExtendedColors.BackgroundVariantEnd,
        )
    )