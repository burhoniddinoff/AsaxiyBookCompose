package com.example.asaxiybookcompose.presentation.screen.audio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import com.example.asaxiybookcompose.ui.theme.AsaxiyBookComposeTheme

class AudioScreen : Screen {
    @Composable
    override fun Content() {
        AudioContent()
    }
}

@Composable
fun AudioContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {

    }

}

@Preview
@Composable
private fun PreviewContent() {
    AsaxiyBookComposeTheme {
        AudioContent()
    }
}
