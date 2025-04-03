package com.hrishi.scribbledash.presentation.one_round_wonder

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OneRoundWonderScreenRoot(
    modifier: Modifier = Modifier
) {
    OneRoundWonderScreen()
}

@Composable
fun OneRoundWonderScreen() {
    Text("One Round Wonder Screen")
}

@Preview
@Composable
fun PreviewOneRoundWonderScreen() {
    OneRoundWonderScreen()
}