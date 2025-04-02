package com.hrishi.scribbledash.home.presentation.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hrishi.scribbledash.designsystem.ScribbleDashTheme

@Composable
fun HomeScreenRoot(
    modifier: Modifier = Modifier
) {
    HomeScreen()
}

@Composable
private fun HomeScreen() {
    Text("Home Screen")
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    ScribbleDashTheme {
        HomeScreen()
    }
}