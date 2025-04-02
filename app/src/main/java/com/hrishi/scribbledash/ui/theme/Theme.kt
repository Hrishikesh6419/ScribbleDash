package com.hrishi.scribbledash.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    tertiaryContainer = TertiaryContainer,
    error = Error,
    background = Background,
    onBackground = OnBackground,
    surfaceContainerHigh = SurfaceHigh,
    surfaceContainerLow = SurfaceLow,
    surfaceContainerLowest = SurfaceLowest,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant
)

val ColorScheme.success: Color
    get() = ExtendedColors.Success

val ColorScheme.onBackgroundVariant: Color
    get() = ExtendedColors.OnBackgroundVariant

@Composable
fun ScribbleDashTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = Typography,
        content = content
    )
}