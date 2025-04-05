package com.hrishi.scribbledash.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hrishi.scribbledash.designsystem.ScribbleDashTheme
import com.hrishi.scribbledash.designsystem.componentDimensions
import com.hrishi.scribbledash.designsystem.spacing

@Composable
fun DrawingCanvas(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .shadow(MaterialTheme.componentDimensions.smallShadow, RoundedCornerShape(MaterialTheme.spacing.extraLarge2))
            .background(color = Color.White, shape = RoundedCornerShape(MaterialTheme.spacing.extraLarge2)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Canvas",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDrawingCanvas() {
    ScribbleDashTheme {
        Column(modifier = Modifier.fillMaxSize()
            .padding(MaterialTheme.spacing.large),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DrawingCanvas(modifier = Modifier.size(MaterialTheme.componentDimensions.canvasSize))
        }
    }
}