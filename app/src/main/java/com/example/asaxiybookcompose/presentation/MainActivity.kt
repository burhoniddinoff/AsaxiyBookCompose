package com.example.asaxiybookcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.example.asaxiybookcompose.navigation.AppNavigationHandler
import com.example.asaxiybookcompose.presentation.screen.splash.SplashScreen
import com.example.asaxiybookcompose.ui.theme.AsaxiyBookComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var handler: AppNavigationHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AsaxiyBookComposeTheme {

                Navigator(screen = SplashScreen())

            }
        }
    }
}