package com.hrishi.scribbledash.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

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

@Composable
fun ScribbleDashTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colorScheme = ColorScheme,
            typography = Typography,
            content = content
        )
    }
}