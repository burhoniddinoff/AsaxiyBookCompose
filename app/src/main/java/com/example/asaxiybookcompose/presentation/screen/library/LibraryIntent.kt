package com.example.asaxiybookcompose.presentation.screen.library

import com.example.asaxiybookcompose.data.data.BookUIData

interface LibraryIntent {
    data object GetAllCategoryList : LibraryIntent
    data object SearchClick : LibraryIntent

    data class BookClick(val product: BookUIData) : LibraryIntent
}