package com.example.asaxiybookcompose.domain

import com.example.asaxiybookcompose.data.data.BookUIData
import com.sudo_pacman.asaxiybooks.data.model.CategoryByBooksData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

interface AppRepository {
    val booksList: MutableSharedFlow<List<BookUIData>>
    val bookLoadError: MutableSharedFlow<String>
    fun getBooks()
    fun getCategoryByAudioBooks(): Flow<Result<List<CategoryByBooksData>>>
    fun getCategoryByPdfBooks(): Flow<Result<List<CategoryByBooksData>>>
    fun setData(books: List<BookUIData>): Flow<Result<Unit>>

    fun getBooksByName(name: String): Flow<Result<List<BookUIData>>>

//    fun getDownloadAudioBooksData(): Flow<Result<List<BookUIData>>>
//    fun getDownloadPdfBooksData(): Flow<Result<List<BookUIData>>>
//    fun getUserBoughtBooks(): Flow<Result<List<BookUIData>>>

}