package com.example.asaxiybookcompose.presentation.screen.log_in

interface MenuIntent {
    data class SignScreen(val gmail: String, val passWord: String) : MenuIntent
    data object ButtonClick : MenuIntent
}