package com.example.asaxiybookcompose.presentation.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen


class RegisterScreen : Screen {

    @Composable
    override fun Content() {
        RegisterContent()
    }


}

@Composable
fun RegisterContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {

    }

}