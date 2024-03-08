package com.example.asaxiybookcompose.presentation.screen.search

import com.example.asaxiybookcompose.data.data.BookUIData

interface SearchIntent {
    data object BackScreen : SearchIntent
    data class NextScreen(val data: BookUIData) : SearchIntent
    data class ResultSearchBook(val text: String) : SearchIntent
}