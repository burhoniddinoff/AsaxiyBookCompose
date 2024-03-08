package com.example.asaxiybookcompose.domain.Impl

import com.example.asaxiybookcompose.domain.AppRepository
import com.example.asaxiybookcompose.myLog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.asaxiybookcompose.data.data.BookUIData
import com.sudo_pacman.asaxiybooks.data.model.CategoryByBooksData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
//    private val bookDao: BookDao,
) : AppRepository {
    private val fireStore = Firebase.firestore

    override val booksList: MutableSharedFlow<List<BookUIData>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    override val bookLoadError: MutableSharedFlow<String> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)

    override fun getBooks() {
        fireStore
            .collection("books_data")
            .addSnapshotListener { value, error ->
                val books = mutableListOf<BookUIData>()
                value?.forEach { snapshot ->
                    books.add(
                        BookUIData(
                            docID = snapshot.id,
                            audioUrl = snapshot.data.getOrDefault("audioUrl", "").toString(),
                            author = snapshot.data.getOrDefault("author", "").toString(),
                            bookUrl = snapshot.data.getOrDefault("bookUrl", "").toString(),
                            categoryId = snapshot.data.getOrDefault("categoryId", "").toString(),
                            coverImage = snapshot.data.getOrDefault("coverImage", "").toString(),
                            description = snapshot.data.getOrDefault("description", "").toString(),
                            filePath = snapshot.data.getOrDefault("filePath", "").toString(),
                            name = snapshot.data.getOrDefault("name", "").toString(),
                            totalSize = snapshot.data.getOrDefault("totalSize", "").toString(),
                            type = snapshot.data.getOrDefault("pdf", "").toString(),
                        )
                    )
                }

                if (error != null) bookLoadError.tryEmit(error.message.toString())
                booksList.tryEmit(books)
            }
    }

    override fun getCategoryByAudioBooks(): Flow<Result<List<CategoryByBooksData>>> =
        callbackFlow<Result<List<CategoryByBooksData>>> {
            val categoryList = ArrayList<CategoryByBooksData>()
            var count = 0
            fireStore.collection("category")
                .addSnapshotListener { value, _ ->
                    value?.forEach {
                        val categoryName = it.data.getOrDefault("name", "") as String
                        val categoryId = it.id
                        fireStore.collection("books_data")
                            .whereEqualTo("categoryId", categoryId)
                            .get()
                            .addOnSuccessListener { qs ->
                                count++
                                val listBookData = ArrayList<BookUIData>()
                                qs.forEach { snapshot ->
                                    val type = snapshot.data.getOrDefault("type", "")
                                        .toString()
                                    if (type == "mp3") {
                                        val data =
                                            BookUIData(
                                                docID = snapshot.id,
                                                audioUrl = snapshot.data.getOrDefault(
                                                    "audioUrl",
                                                    ""
                                                )
                                                    .toString(),
                                                author = snapshot.data.getOrDefault("author", "")
                                                    .toString(),
                                                bookUrl = snapshot.data.getOrDefault("bookUrl", "")
                                                    .toString(),
                                                categoryId = snapshot.data.getOrDefault(
                                                    "categoryId",
                                                    ""
                                                )
                                                    .toString(),
                                                coverImage = snapshot.data.getOrDefault(
                                                    "coverImage",
                                                    ""
                                                )
                                                    .toString(),
                                                description = snapshot.data.getOrDefault(
                                                    "description",
                                                    ""
                                                )
                                                    .toString(),
                                                filePath = snapshot.data.getOrDefault(
                                                    "filePath",
                                                    ""
                                                )
                                                    .toString(),
                                                name = snapshot.data.getOrDefault("name", "")
                                                    .toString(),
                                                totalSize = snapshot.data.getOrDefault(
                                                    "totalSize",
                                                    ""
                                                )
                                                    .toString(),
                                                type = type,
                                            )
                                        listBookData.add(data)
                                    }
                                }
                                if (listBookData.isNotEmpty()) {
                                    categoryList.add(
                                        CategoryByBooksData(
                                            categoryName = categoryName,
                                            categoryId = categoryId,
                                            books = listBookData,
                                            type = 10
                                        )
                                    )
                                }
                                if (count == value.size()) {
                                    trySend(Result.success(categoryList))
                                }
                            }
                            .addOnFailureListener { exp ->
                                trySend(Result.failure(exp))
                            }
                    }
                }
            awaitClose()
        }.flowOn(Dispatchers.IO)

    override fun getCategoryByPdfBooks(): Flow<Result<List<CategoryByBooksData>>> =
        callbackFlow<Result<List<CategoryByBooksData>>> {
            val categoryList = ArrayList<CategoryByBooksData>()
            var count = 0
            fireStore.collection("category")
                .addSnapshotListener { value, _ ->
                    value?.forEach {
                        val categoryName = it.data.getOrDefault("name", "") as String
                        val categoryId = it.id
                        fireStore.collection("books_data")
                            .whereEqualTo("categoryId", categoryId)
                            .get()
                            .addOnSuccessListener { qs ->
                                count++
                                val listBookData = ArrayList<BookUIData>()
                                qs.forEach { snapshot ->
                                    val type = snapshot.data.getOrDefault("type", "").toString()
                                    if (type == "pdf") {
                                        val data =
                                            BookUIData(
                                                docID = snapshot.id,
                                                audioUrl = snapshot.data.getOrDefault(
                                                    "audioUrl",
                                                    ""
                                                ).toString(),
                                                author = snapshot.data.getOrDefault("author", "")
                                                    .toString(),
                                                bookUrl = snapshot.data.getOrDefault("bookUrl", "")
                                                    .toString(),
                                                categoryId = snapshot.data.getOrDefault(
                                                    "categoryId",
                                                    ""
                                                )
                                                    .toString(),
                                                coverImage = snapshot.data.getOrDefault(
                                                    "coverImage",
                                                    ""
                                                )
                                                    .toString(),
                                                description = snapshot.data.getOrDefault(
                                                    "description",
                                                    ""
                                                )
                                                    .toString(),
                                                filePath = snapshot.data.getOrDefault(
                                                    "filePath",
                                                    ""
                                                )
                                                    .toString(),
                                                name = snapshot.data.getOrDefault("name", "")
                                                    .toString(),
                                                totalSize = snapshot.data.getOrDefault(
                                                    "totalSize",
                                                    ""
                                                )
                                                    .toString(),
                                                type = type,
                                            )
                                        listBookData.add(data)
                                    }
                                }
                                if (listBookData.isNotEmpty()) {
                                    categoryList.add(
                                        CategoryByBooksData(
                                            categoryName = categoryName,
                                            categoryId = categoryId,
                                            books = listBookData,
                                            type = 10
                                        )
                                    )
                                }
                                if (count == value.size()) {
                                    trySend(Result.success(categoryList))
                                }
                            }
                            .addOnFailureListener { exp ->
                                trySend(Result.failure(exp))
                            }
                    }
                }
            awaitClose()
        }.flowOn(Dispatchers.IO)

    override fun setData(books: List<BookUIData>): Flow<Result<Unit>> = callbackFlow {

        for (index in books.indices) {
            fireStore
                .collection("books_data")
                .add(books[index])
                .addOnSuccessListener {

                }
                .addOnFailureListener {

                }
        }

        awaitClose()
    }

    override fun getBooksByName(name: String): Flow<Result<List<BookUIData>>> =
        channelFlow<Result<List<BookUIData>>> {

            ("name -> #$name#  " + name.trim().isEmpty()).myLog("BUSH")
            if (name.trim().isEmpty())
                trySend(Result.success(arrayListOf()))
            else {
                fireStore.collection("books_data")
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        val booksList = ArrayList<BookUIData>()
                        querySnapshot.forEach { snapshot ->
                            val bookName = snapshot.data.getOrDefault("name", "").toString()
                            if (bookName.lowercase().contains(name.lowercase())) {
                                val data =
                                    BookUIData(
                                        docID = snapshot.id,
                                        audioUrl = snapshot.data.getOrDefault("audioUrl", "")
                                            .toString(),
                                        author = snapshot.data.getOrDefault("author", "")
                                            .toString(),
                                        bookUrl = snapshot.data.getOrDefault("bookUrl", "")
                                            .toString(),
                                        categoryId = snapshot.data.getOrDefault(
                                            "categoryId",
                                            ""
                                        )
                                            .toString(),
                                        coverImage = snapshot.data.getOrDefault(
                                            "coverImage",
                                            ""
                                        )
                                            .toString(),
                                        description = snapshot.data.getOrDefault(
                                            "description",
                                            ""
                                        )
                                            .toString(),
                                        filePath = snapshot.data.getOrDefault("filePath", "")
                                            .toString(),
                                        name = bookName,
                                        totalSize = snapshot.data.getOrDefault("totalSize", "")
                                            .toString(),
                                        type = snapshot.data.getOrDefault("type", "")
                                            .toString(),
                                    )
                                booksList.add(data)
                            }
                        }
                        booksList.size.toString().myLog("BAHA")
                        trySend(Result.success(booksList))
                    }
                    .addOnFailureListener { ex ->
                        trySend(Result.failure(ex))
                    }
            }
            awaitClose()

        }.flowOn(Dispatchers.IO).catch { emit(Result.failure(it)) }

//    override fun getDownloadAudioBooksData(): Flow<Result<List<BookUIData>>> =
//        callbackFlow {
//            trySend(Result.success(bookDao.getAllBooks().filter { it.type == "mp3" }
//                .map { it.toUiData() }))
//            awaitClose()
//        }.flowOn(Dispatchers.IO)
//
//    override fun getDownloadPdfBooksData(): Flow<Result<List<BookUIData>>> =
//        callbackFlow<Result<List<BookUIData>>> {
//            trySend(Result.success(bookDao.getAllBooks().filter { it.type == "pdf" }
//                .map { it.toUiData() }))
//            awaitClose()
//        }.flowOn(Dispatchers.IO)
//
//    override fun getUserBoughtBooks(): Flow<Result<List<BookUIData>>> =
//        callbackFlow<Result<List<BookUIData>>> {
//            MySharedPreference.getUserData()
//                .booksId.forEach { bookId ->
//                    fireStore.collection("books_data")
//                        .get()
//                        .addOnSuccessListener { qs ->
//                            val bookList = ArrayList<BookUIData>()
//                            qs.forEach { snapshot ->
//                                if (bookId == snapshot.id) {
//                                    val data =
//                                        BookUIData(
//                                            docID = snapshot.id,
//                                            audioUrl = snapshot.data.getOrDefault("audioUrl", "")
//                                                .toString(),
//                                            author = snapshot.data.getOrDefault("author", "")
//                                                .toString(),
//                                            bookUrl = snapshot.data.getOrDefault("bookUrl", "")
//                                                .toString(),
//                                            categoryId = snapshot.data.getOrDefault(
//                                                "categoryId",
//                                                ""
//                                            )
//                                                .toString(),
//                                            coverImage = snapshot.data.getOrDefault(
//                                                "coverImage",
//                                                ""
//                                            )
//                                                .toString(),
//                                            description = snapshot.data.getOrDefault(
//                                                "description",
//                                                ""
//                                            )
//                                                .toString(),
//                                            filePath = snapshot.data.getOrDefault("filePath", "")
//                                                .toString(),
//                                            name = snapshot.data.getOrDefault("name", "")
//                                                .toString(),
//                                            totalSize = snapshot.data.getOrDefault("totalSize", "")
//                                                .toString(),
//                                            type = snapshot.data.getOrDefault("type", "")
//                                                .toString(),
//                                        )
//                                    bookList.add(data)
//                                }
//                            }
//                            trySend(Result.success(bookList))
//                        }
//                        .addOnFailureListener { ex ->
//                            trySend(Result.failure(ex))
//                        }
//                }
//            awaitClose()
//        }.flowOn(Dispatchers.IO)
}