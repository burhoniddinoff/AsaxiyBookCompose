package com.example.asaxiybookcompose.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.asaxiybookcompose.R
import com.example.asaxiybookcompose.presentation.screen.register.RegisterScreen
import com.example.asaxiybookcompose.ui.theme.AsaxiyBookComposeTheme
import kotlinx.coroutines.delay

class SplashScreen : Screen {

    @Composable
    override fun Content() {

        val navigation = LocalNavigator.currentOrThrow

        LaunchedEffect(key1 = Unit) {
            delay(2000)
            navigation.push(RegisterScreen())
        }

        ScreenContent()
    }

}

@Composable
fun ScreenContent() {

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.gradient),
            contentDescription = null,
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.asaxiy_logo))
            val progress by animateLottieCompositionAsState(composition)
            LottieAnimation(
                composition = composition,
                progress = { progress },
            )
        }


    }

}

@Preview
@Composable
private fun PreviewContent() {
    AsaxiyBookComposeTheme {
        ScreenContent()
    }
}