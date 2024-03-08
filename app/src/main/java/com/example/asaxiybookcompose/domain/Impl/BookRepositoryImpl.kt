package com.example.asaxiybookcompose.domain.Impl

import com.example.asaxiybookcompose.data.dao.BookDao
import com.example.asaxiybookcompose.data.data.BookUIData
import com.example.asaxiybookcompose.data.data.UploadData
import com.example.asaxiybookcompose.domain.BookRepository
import com.example.asaxiybookcompose.myLog
import com.example.asaxiybookcompose.toEntityBookData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookDao: BookDao,
) : BookRepository {
    private val fireStorage = Firebase.storage
    private val fireStore = Firebase.firestore
    private var loadTask: FileDownloadTask? = null

    override fun downloadBookWithProgress(data: BookUIData): Flow<UploadData> = callbackFlow {
        "repo yuklab olish kerak $data".myLog()
        "repo yuklab olish kerak bo'lkan book url ${data.bookUrl}".myLog()
        val book = File.createTempFile("Pdf", ".pdf")
        loadTask = fireStorage
            .getReferenceFromUrl(data.bookUrl)
            .getFile(book)

        loadTask!!
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

    override fun getBook(bookData: BookUIData): Flow<Result<File>> = callbackFlow {
        val bookId = bookDao.isHas(bookData.bookUrl)
        "repo getBook bunaqasi borakan".myLog()

        trySend(Result.success(File(bookDao.getBooksById(id = bookId).filePath)))

        awaitClose()
    }

    override fun isDownload(bookUIData: BookUIData): Boolean {
        val bookId = bookDao.isHas(bookUIData.bookUrl)
        return if (bookId != 0L) {
            "repo isDownload bunaqasi borakan".myLog()
            true
        } else false
    }

}