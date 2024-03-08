package com.example.asaxiybookcompose.presentation.screen.audio

import com.example.asaxiybookcompose.data.data.BookUIData

interface AudioIntent {
    data object GetAllCategoryList : AudioIntent
    data class ButtonClick(val data: BookUIData) : AudioIntent
}