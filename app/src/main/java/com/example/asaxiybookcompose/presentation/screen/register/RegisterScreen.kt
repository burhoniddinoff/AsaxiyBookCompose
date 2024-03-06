package com.example.asaxiybookcompose.presentation.screen.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.asaxiybookcompose.R
import com.example.asaxiybookcompose.ui.theme.AsaxiyBookComposeTheme

class RegisterScreen : Screen {

    @Composable
    override fun Content() {

        ScreenContent()

    }

}

@Composable
fun ScreenContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0e1629))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_logo_1),
                contentDescription = "img1",
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.Center)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_logo_2),
                contentDescription = "img1",
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.Center)
            )

        }

    }

}

@Preview
@Composable
private fun PreviewContent() {
    MaterialTheme {
        ScreenContent()
    }
}