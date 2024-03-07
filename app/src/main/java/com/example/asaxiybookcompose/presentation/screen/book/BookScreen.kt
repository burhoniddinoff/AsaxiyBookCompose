package com.example.asaxiybookcompose.presentation.screen.book

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import com.example.asaxiybookcompose.ui.theme.AsaxiyBookComposeTheme

class BookScreen : Screen {
    @Composable
    override fun Content() {
        BookContent()
    }
}

@Composable
fun BookContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {

    }

}

@Preview
@Composable
private fun PreviewContent() {
    AsaxiyBookComposeTheme {
        BookContent()
    }
}