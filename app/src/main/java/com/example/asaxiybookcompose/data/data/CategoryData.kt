package com.example.asaxiybookcompose.data.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryData(
    val id: String,
    val categoryName: String,
    val ls: List<BookUIData>,
) : Parcelable
