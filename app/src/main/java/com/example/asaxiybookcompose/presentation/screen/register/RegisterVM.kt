package com.example.asaxiybookcompose.presentation.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asaxiybookcompose.myLog
import com.example.asaxiybookcompose.navigation.AppNavigator
import com.example.asaxiybookcompose.presentation.screen.main.MainScreen
import com.example.asaxiybookcompose.presentation.screen.register.RegisterScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterVM @Inject constructor(
    private val navigator: AppNavigator,
) : ViewModel() {

    fun eventDispatcher(intent: RegisterIntent) {
        when (intent) {
            RegisterIntent.Register -> viewModelScope.launch {
                navigator.navigate(MainScreen())
            }
        }
    }

}

sealed class RegisterIntent {
    data object Register : RegisterIntent()
}