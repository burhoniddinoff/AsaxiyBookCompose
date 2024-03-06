package com.example.asaxiybookcompose.presentation.screen.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.example.asaxiybookcompose.R

class RegisterScreen : Screen {

    @Composable
    override fun Content() {

        val textValue by remember { mutableStateOf("") }

        ScreenContent(textValue)

    }

}

@Composable
fun ScreenContent(textValue: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0e1629))
    ) {

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_logo_1),
                contentDescription = "img1",
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.CenterVertically)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_logo_2),
                contentDescription = "img1",
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterVertically)
            )

        }

        Text(
            text = "Xush kelibsiz !",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 72.dp)
        )

        Text(
            text = "Log In",
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp)
        )



        MyText(text = "Emailingizni kiriting")
        MyEditText(textValue = textValue, "Enter your email!")

        MyText(text = "Password")
        MyEditText(textValue = textValue, "Enter your password!")

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 24.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF008dff)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Kirish",
                fontSize = 14.sp
            )
        }



    }

}

@Composable
fun MyText(text: String) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 14.sp,
        modifier = Modifier.padding(top = 32.dp, start = 24.dp)
    )
}

@Composable
fun MyEditText(textValue: String, hint: String) {
    TextField(
        value = textValue,
        onValueChange = {
//                            textValue = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 10.dp),
        placeholder = { Text(hint) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
        maxLines = 1,
        shape = RoundedCornerShape(12.dp),
//            colors = TextFieldDefaults.colors(),
        textStyle = TextStyle(color = Color.Black)
    )
}


@Preview
@Composable
private fun PreviewContent() {
    MaterialTheme {
        ScreenContent("")
    }
}