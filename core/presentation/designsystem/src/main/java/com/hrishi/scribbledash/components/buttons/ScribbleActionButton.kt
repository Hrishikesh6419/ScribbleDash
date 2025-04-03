package com.hrishi.scribbledash.components.buttons

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.hrishi.scribbledash.designsystem.ScribbleDashTheme
import com.hrishi.scribbledash.designsystem.UndoIcon
import com.hrishi.scribbledash.designsystem.appShapes
import com.hrishi.scribbledash.designsystem.componentDimensions
import com.hrishi.scribbledash.designsystem.spacing

@Composable
fun ScribbleActionButton(
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
    iconColor: Color = MaterialTheme.colorScheme.onBackground,
    pressedIconColor: Color = MaterialTheme.colorScheme.onBackground,
    disabledIconColor: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
    containerColor: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    pressedContainerColor: Color = MaterialTheme.colorScheme.surfaceContainerLowest,
    disabledContainerColor: Color = MaterialTheme.colorScheme.surfaceContainerLow.copy(alpha = 0.4f)
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val currentContainerColor = when {
        !isEnabled -> disabledContainerColor
        isPressed -> pressedContainerColor
        else -> containerColor
    }

    val currentIconColor = when {
        !isEnabled -> disabledIconColor
        isPressed -> pressedIconColor
        else -> iconColor
    }

    Surface(
        onClick = onClick,
        modifier = modifier.size(MaterialTheme.componentDimensions.actionButtonSize),
        enabled = isEnabled,
        shape = MaterialTheme.appShapes.large,
        color = currentContainerColor,
        interactionSource = interactionSource
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = currentIconColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScribbleActionButtonPreview() {
    ScribbleDashTheme {
        Surface(color = Color.White) {
            Column(
                modifier = Modifier.padding(MaterialTheme.spacing.medium),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
            ) {
                Text("Different states:", style = MaterialTheme.typography.titleMedium)

                Row(
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
                ) {
                    ScribbleActionButton(
                        onClick = {},
                        icon = UndoIcon,
                        contentDescription = "Undo",
                        isEnabled = true
                    )

                    ScribbleActionButton(
                        onClick = {},
                        icon = UndoIcon,
                        contentDescription = "Undo",
                        isEnabled = false
                    )
                }
            }
        }
    }
}