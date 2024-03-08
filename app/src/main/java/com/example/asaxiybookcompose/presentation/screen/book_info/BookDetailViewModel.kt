package com.example.asaxiybookcompose.presentation.screen.book_info

import androidx.compose.runtime.MutableState

interface BookDetailViewModel {

    val pos: MutableState<Int>
    fun onD(intent: IntentInfo)

}