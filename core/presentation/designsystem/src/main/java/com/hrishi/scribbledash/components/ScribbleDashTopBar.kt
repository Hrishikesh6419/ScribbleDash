package com.hrishi.scribbledash.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.hrishi.scribbledash.designsystem.CloseIcon
import com.hrishi.scribbledash.designsystem.ScribbleDashTheme
import com.hrishi.scribbledash.designsystem.componentDimensions
import com.hrishi.scribbledash.designsystem.spacing

@Composable
fun ScribbleDashTopBar(
    title: String? = null,
    icon: ImageVector? = null,
    onIconClick: (() -> Unit)? = null,
    titleStyle: TextStyle = MaterialTheme.typography.headlineMedium,
    titleColor: Color = MaterialTheme.colorScheme.onBackground,
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    iconSize: Dp = MaterialTheme.componentDimensions.iconSize,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.medium)
            .padding(vertical = MaterialTheme.spacing.mediumLarge)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (title != null) {
                Text(
                    text = title,
                    style = titleStyle.copy(color = titleColor)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier
                        .size(iconSize)
                        .then(
                            if (onIconClick != null) {
                                Modifier.clickable { onIconClick() }
                            } else {
                                Modifier
                            }
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScribbleDashTopBar() {
    ScribbleDashTheme {
        Column {
            ScribbleDashTopBar(
                title = "ScribbleDash",
                icon = CloseIcon
            )

            ScribbleDashTopBar(
                title = "ScribbleDash"
            )

            ScribbleDashTopBar(
                icon = CloseIcon
            )
        }
    }
}