package com.example.asaxiybookcompose.data.data

import java.io.File

sealed interface UploadData {
    data object PAUSE : UploadData
    data object RESUME : UploadData
    data object CANCEL : UploadData

    data class Success(
        val book: File,
    ) : UploadData

    data class Error(
        val message: String,
    ) : UploadData

    data class Progress(
        val uploadBytes: Long,
    ) : UploadData
}