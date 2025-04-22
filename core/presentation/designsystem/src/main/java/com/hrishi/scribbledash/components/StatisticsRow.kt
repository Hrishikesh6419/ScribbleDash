package com.hrishi.scribbledash.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.hrishi.scribbledash.designsystem.BoltIcon
import com.hrishi.scribbledash.designsystem.HourGlassIcon
import com.hrishi.scribbledash.designsystem.ScribbleDashTheme
import com.hrishi.scribbledash.designsystem.appShapes
import com.hrishi.scribbledash.designsystem.componentDimensions
import com.hrishi.scribbledash.designsystem.onBackgroundVariant
import com.hrishi.scribbledash.designsystem.spacing

@Composable
fun StatisticsRow(
    modifier: Modifier = Modifier,
    image: ImageVector = BoltIcon,
    text: String = "Nothing to Track.. for now",
    value: String = "0%",
    contentDescriptionText: String? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceContainerHigh,
    textStyle: TextStyle = MaterialTheme.typography.bodySmall,
    valueStyle: TextStyle = MaterialTheme.typography.headlineLarge,
    textColor: Color = MaterialTheme.colorScheme.onBackgroundVariant,
    valueColor: Color = MaterialTheme.colorScheme.onBackground,
    iconTint: Color = Color.Unspecified,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.appShapes.extraLarge1,
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = MaterialTheme.componentDimensions.smallShadow)
    ) {
        Row(
            modifier = Modifier
                .padding(MaterialTheme.spacing.smallMedium)
                .padding(end = MaterialTheme.spacing.small)
                .semantics(mergeDescendants = true) {
                    contentDescription = contentDescriptionText ?: "$text: $value"
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                tint = iconTint,
                imageVector = image,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.smallMedium))

            Text(
                text = text,
                style = textStyle.copy(color = textColor)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = value,
                style = valueStyle.copy(color = valueColor)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewStatisticsRow() {
    ScribbleDashTheme {
        Column(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .background(MaterialTheme.colorScheme.background)
        ) {
            StatisticsRow(
                image = BoltIcon,
                text = "Nothing to Track.. for now",
                value = "0%"
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            StatisticsRow(
                image = HourGlassIcon,
                text = "Nothing to Track.. for now",
                value = "0"
            )
        }
    }
}