package com.example.asaxiybookcompose.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asaxiybookcompose.myLog
import com.example.asaxiybookcompose.navigation.AppNavigator
import com.example.asaxiybookcompose.presentation.screen.register.RegisterScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashVM @Inject constructor(
    private val navigator: AppNavigator,
) : ViewModel() {

    fun eventDispatcher(intent: SplashIntent) {
        when (intent) {
            SplashIntent.Register -> viewModelScope.launch {
                navigator.replace(RegisterScreen())
                "eventDispatcher".myLog()
            }
        }
    }

}

sealed class SplashIntent {
    data object Register : SplashIntent()
}