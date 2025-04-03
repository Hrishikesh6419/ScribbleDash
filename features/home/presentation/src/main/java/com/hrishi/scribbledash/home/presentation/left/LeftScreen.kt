package com.hrishi.scribbledash.home.presentation.left

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hrishi.scribbledash.designsystem.ScribbleDashTheme

@Composable
fun LeftScreenRoot(
    modifier: Modifier = Modifier
) {
    LeftScreen()
}

@Composable
private fun LeftScreen() {
    Text("Left Screen")
}

@Preview
@Composable
private fun PreviewLeftScreen() {
    ScribbleDashTheme {
        LeftScreen()
    }
}