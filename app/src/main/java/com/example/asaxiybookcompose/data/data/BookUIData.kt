package com.example.asaxiybookcompose.data.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookUIData(
    var docID: String,
    var audioUrl: String,
    var author: String,
    var bookUrl: String,
    var categoryId: String,
    var coverImage: String,
    var description: String,
    var filePath: String,
    var name: String,
    var totalSize: String,
    var type: String,
) : Parcelable