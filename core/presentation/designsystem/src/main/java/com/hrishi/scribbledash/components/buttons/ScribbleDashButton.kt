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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hrishi.scribbledash.designsystem.ScribbleDashTheme
import com.hrishi.scribbledash.designsystem.success

@Composable
fun ScribbleDashButton(
    modifier: Modifier = Modifier,
    text: String,
    isEnabled: Boolean = false,
    enabledBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    disabledBackgroundColor: Color = MaterialTheme.colorScheme.surfaceContainerLowest,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
            .padding(6.dp)
    ) {
        Button(
            onClick = onClick,
            enabled = isEnabled,
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = enabledBackgroundColor,
                disabledContainerColor = disabledBackgroundColor,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 52.dp)
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
                .padding(16.dp)
        ) {
            ScribbleDashButton(
                text = "START!",
                onClick = {},
            )

            Spacer(modifier = Modifier.height(16.dp))

            ScribbleDashButton(
                text = "START!",
                onClick = {},
                isEnabled = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            ScribbleDashButton(
                text = "START!",
                onClick = {},
                isEnabled = true,
                enabledBackgroundColor = MaterialTheme.colorScheme.success
            )
        }
    }
}