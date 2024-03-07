package com.example.asaxiybookcompose.presentation.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.asaxiybookcompose.R
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
            .background(Color(0xFF0e1629))
    ) {

        Text(
            text = "Profil",
            fontSize = 24.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.roboto_medium)),
            modifier = Modifier
                .padding(start = 14.dp, top = 14.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 14.dp)
        ) {


            Image(
                painter = painterResource(id = R.drawable.ic_one_piece),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 5.dp)
                    .size(60.dp)
                    .clip(shape = CircleShape),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Abdulqodir Burhoniddinov",
                    color = Color.White,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(start = 20.dp)
                )

                Text(
                    text = "Tahrirlash uchun ustiga bosing!",
                    color = Color(0xFF8AFFFFFF),
                    fontSize = 17.sp,
                    modifier = Modifier.padding(start = 20.dp, top = 2.dp)

                )
            }

        }


    }

}

@Preview
@Composable
private fun PreviewContent() {
    AsaxiyBookComposeTheme {
        ProfileContent()
    }
}

