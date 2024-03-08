package com.sudo_pacman.asaxiybooks.data.model

import android.os.Parcelable
import com.example.asaxiybookcompose.data.data.BookUIData
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryByBooksData(
    val categoryId: String,
    val categoryName: String,
    val books: List<BookUIData>,
    val type: Int,
) : Parcelable