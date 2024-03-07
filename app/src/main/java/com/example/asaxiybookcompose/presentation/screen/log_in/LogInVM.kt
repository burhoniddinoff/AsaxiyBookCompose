package com.example.asaxiybookcompose.presentation.screen.log_in

import kotlinx.coroutines.flow.StateFlow

interface LogInVM {

    val uiState: StateFlow<Unit>

    fun onDispatch(intent: MenuIntent)

    sealed interface MenuIntent {
        data class SignScreen(val gmail: String, val passWord: String) : MenuIntent
        data object ButtonClick : MenuIntent
    }

    fun logInRequest(gmail: String, password: String)

}