package com.example.asaxiybookcompose.presentation.screen.library

interface LibraryIntent {
    data object GetAllCategoryList : LibraryIntent
    data object ButtonClick : LibraryIntent
}