package com.example.asaxiybookcompose.presentation.screen.book_info

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asaxiybookcompose.data.data.BookUIData
import com.example.asaxiybookcompose.data.source.MySharedPreference
import com.example.asaxiybookcompose.domain.AppRepository
import com.example.asaxiybookcompose.myLog
import com.example.asaxiybookcompose.navigation.AppNavigator
import com.example.asaxiybookcompose.presentation.screen.info.InfoScreen
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModelImpl @Inject constructor(
    private val booksRepository: AppRepository,
    private val navigator: AppNavigator,
) : ViewModel(), BookDetailViewModel {

    private val pref = MySharedPreference

    var counter = 0
    override val pos: MutableState<Int> = mutableIntStateOf(0)


    override fun onD(intent: IntentInfo) {
        when (intent) {
            is IntentInfo.Download -> {
                downloadBook(intent.bookData)
            }

            is IntentInfo.Read -> {
                viewModelScope.launch {
                    navigator.navigate(InfoScreen(intent.bookData))
                }
            }


        }
    }

    private fun downloadBook(bookData: BookUIData) {
        val book = File.createTempFile(bookData.name, ".${bookData.type}")
        val downland = Firebase.storage.getReferenceFromUrl(bookData.filePath)
            .getFile(book)

        downland.addOnSuccessListener {
            pref.setDownlandBookId(bookId = bookData.categoryId)
            pos.value = if (pos.value == 0) 1 else 0
        }
            .addOnFailureListener {
                "Hato".myLog()
            }
    }
}