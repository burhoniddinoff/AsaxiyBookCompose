package com.example.asaxiybookcompose.domain.Impl

import com.example.asaxiybookcompose.data.data.BookUIData
import com.example.asaxiybookcompose.domain.BookRepository
import com.example.asaxiybookcompose.myLog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor() : BookRepository {
    private val fireStorage = Firebase.storage
    private val fireStore = Firebase.firestore

    fun downloadBookWithProgress(data: BookUIData): Flow<Unit> = callbackFlow {
        "repo yuklab olish kerak $data".myLog()
        "repo yuklab olish kerak bo'lkan book url ${data.bookUrl}".myLog()
        val book = File.createTempFile("Pdf", ".pdf")
       fireStorage
            .getReferenceFromUrl(data.bookUrl)
            .getFile(book)
            .addOnSuccessListener {
                bookDao.setBook(data.toEntityBookData(book.absolutePath))

                trySend(UploadData.Success(File(book.absolutePath)))

            }
            .addOnProgressListener {
                trySend(UploadData.Progress(it.bytesTransferred * 100 / it.totalByteCount))
            }

        awaitClose()
    }
        .flowOn(Dispatchers.IO)

}