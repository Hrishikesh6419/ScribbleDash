package com.hrishi.scribbledash.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hrishi.scribbledash.designsystem.ScribbleDashTheme
import com.hrishi.scribbledash.designsystem.appShapes
import com.hrishi.scribbledash.designsystem.componentDimensions
import com.hrishi.scribbledash.designsystem.spacing
import com.hrishi.scribbledash.designsystem.success

@Composable
fun ScribbleDashButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = false,
    enabledBackgroundColor: Color = MaterialTheme.colorScheme.success,
    disabledBackgroundColor: Color = MaterialTheme.colorScheme.surfaceContainerLowest,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.appShapes.extraLarge)
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .padding(MaterialTheme.componentDimensions.buttonBorderPadding)
    ) {
        Button(
            onClick = onClick,
            enabled = isEnabled,
            shape = MaterialTheme.appShapes.large,
            colors = ButtonDefaults.buttonColors(
                containerColor = enabledBackgroundColor,
                disabledContainerColor = disabledBackgroundColor,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = MaterialTheme.componentDimensions.buttonMinHeight)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun ScribbleDashButtonPreview() {
    ScribbleDashTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium)
        ) {
            ScribbleDashButton(
                text = "START!",
                onClick = {},
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            ScribbleDashButton(
                text = "START!",
                onClick = {},
                isEnabled = true
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }
    }
}