package com.example.asaxiybookcompose.domain

import com.example.asaxiybookcompose.data.data.BookUIData
import com.example.asaxiybookcompose.data.data.UploadData
import kotlinx.coroutines.flow.Flow
import java.io.File

interface BookRepository {
    fun isDownload(bookUIData: BookUIData): Boolean
    fun downloadBookWithProgress(data: BookUIData): Flow<UploadData>
    fun getBook(bookData: BookUIData): Flow<Result<File>>
}