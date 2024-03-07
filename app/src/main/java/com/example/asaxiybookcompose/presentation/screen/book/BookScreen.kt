package com.example.asaxiybookcompose.presentation.screen.book

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.asaxiybookcompose.R
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
            .background(color = Color(0xFF0F172B))
    ) {

        Text(
            text = "Mening kitoblarim",
            fontSize = 24.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.roboto_medium)),
            modifier = Modifier.padding(start = 14.dp, top = 14.dp)
        )

    }

}

@Preview
@Composable
private fun PreviewContent() {
    AsaxiyBookComposeTheme {
        BookContent()
    }
}