package com.example.asaxiybookcompose.presentation.screen.log_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asaxiybookcompose.domain.RegisterRepository
import com.example.asaxiybookcompose.myLog
import com.example.asaxiybookcompose.navigation.AppNavigator
import com.example.asaxiybookcompose.presentation.screen.main.MainScreen
import com.example.asaxiybookcompose.presentation.screen.register.RegisterScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInVMImpl @Inject constructor(
    private val repository: RegisterRepository,
    private val navigator: AppNavigator,
) : ViewModel(), LogInVM {
    override val uiState = MutableStateFlow(Unit)

    override fun onDispatch(intent: MenuIntent) {

        when (intent) {

            MenuIntent.ButtonClick -> {
                registerScreen()

            }

            is MenuIntent.SignScreen -> {
//                logInRequest(intent.gmail, intent.passWord)
                nextScreen()
            }
        }

    }

    override fun logInRequest(gmail: String, password: String) {
        repository.loginUser(password, gmail).onEach { it ->
            it.onSuccess {
                nextScreen()
                "logInRequest: next screen -> $it".myLog()
            }
            it.onFailure {
                "logInRequest: error -> $it".myLog()
            }

        }.launchIn(viewModelScope)
    }

    private fun nextScreen() {
        "nextScreen() ".myLog()
        viewModelScope.launch {
            navigator.navigate(MainScreen())
        }
        "nextScreen() ".myLog()
    }

    private fun registerScreen() {
        "register() ".myLog()
        viewModelScope.launch {
            navigator.navigate(RegisterScreen())
        }
        "register() ".myLog()
    }


}

