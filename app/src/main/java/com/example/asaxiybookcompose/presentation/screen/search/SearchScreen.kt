package com.example.asaxiybookcompose.presentation.screen.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.asaxiybookcompose.R
import com.example.asaxiybookcompose.ui.theme.AsaxiyBookComposeTheme

class SearchScreen : Screen {
    @Composable
    override fun Content() {
        SearchContent()
    }
}

@Composable
fun SearchContent() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F172B))
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "back",
            modifier = Modifier
                .size(56.dp)
                .padding(16.dp)
//                .clickable {
//                    eventListener(AudioItemIntent.OnClickBack)
//                }
        )
    }

}

@Preview
@Composable
private fun PrevContent() {

    AsaxiyBookComposeTheme {
        SearchContent()
    }

}