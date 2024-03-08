package com.example.asaxiybookcompose.presentation.screen.book_info

import com.example.asaxiybookcompose.data.data.BookUIData

interface IntentInfo {
//    data class Buy(
//        val bookId: String,
//    ) : IntentInfo

    data class Read(
        val bookData: BookUIData,
    ) : IntentInfo

    data class Download(
        val bookData: BookUIData,
    ) : IntentInfo
}