package com.example.asaxiybookcompose.presentation.screen.search

interface SearchIntent {
    data object BackScreen : SearchIntent
    data class ResultSearchBook(val text: String) : SearchIntent
}