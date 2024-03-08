package com.example.asaxiybookcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.example.asaxiybookcompose.myLog
import com.example.asaxiybookcompose.navigation.AppNavigationHandler
import com.example.asaxiybookcompose.presentation.screen.splash.SplashScreen
import com.example.asaxiybookcompose.ui.theme.AsaxiyBookComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    init {
        "log".myLog()
    }

    @Inject
    lateinit var handler: AppNavigationHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AsaxiyBookComposeTheme {

                Navigator(screen = SplashScreen()) { navigator ->
                    LaunchedEffect(key1 = navigator) {
                        handler.backStage
                            .onEach { it(navigator) }
                            .launchIn(lifecycleScope)
                    }
                    CurrentScreen()
                }

            }
        }
    }
}