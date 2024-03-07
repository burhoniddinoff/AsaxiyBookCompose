package com.example.asaxiybookcompose.presentation.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import com.example.asaxiybookcompose.ui.theme.AsaxiyBookComposeTheme

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        ProfileContent()
    }
}

@Composable
fun ProfileContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {

    }

}

@Preview
@Composable
private fun PreviewContent() {
    AsaxiyBookComposeTheme {
        ProfileContent()
    }
}