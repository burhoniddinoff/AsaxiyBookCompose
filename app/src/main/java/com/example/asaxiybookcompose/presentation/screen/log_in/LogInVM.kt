package com.example.asaxiybookcompose.presentation.screen.log_in

import kotlinx.coroutines.flow.StateFlow

interface LogInVM {

    val uiState: StateFlow<Unit>

    fun onDispatch(intent: MenuIntent)

    fun logInRequest(gmail: String, password: String)

}