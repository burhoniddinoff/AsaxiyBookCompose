package com.example.asaxiybookcompose.presentation.screen.library

import com.example.asaxiybookcompose.data.data.BookUIData

interface LibraryIntent {
    data object GetAllCategoryList : LibraryIntent
    data object ButtonClick : LibraryIntent

    data class BookClick(val product: BookUIData) : LibraryIntent
}