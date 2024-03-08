package com.example.asaxiybookcompose.presentation.screen.audio_item

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import com.example.asaxiybookcompose.ui.theme.AsaxiyBookComposeTheme

class AudioItemScreen : Screen {
    @Composable
    override fun Content() {
        AudioItemContent()
    }
}

@Composable
fun AudioItemContent() {



}



@Preview
@Composable
private fun AudioItemPrev() {
    AsaxiyBookComposeTheme {

    }
}