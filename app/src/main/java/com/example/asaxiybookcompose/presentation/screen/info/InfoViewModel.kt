package com.example.asaxiybookcompose.presentation.screen.info

import com.example.asaxiybookcompose.data.data.BookUIData

interface InfoViewModel {

    data class OpenBook(val bookData: BookUIData) : InfoViewModel

}