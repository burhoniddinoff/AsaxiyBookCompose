package com.example.asaxiybookcompose.presentation.screen.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asaxiybookcompose.data.data.BookUIData
import com.example.asaxiybookcompose.data.data.UploadData
import com.example.asaxiybookcompose.domain.BookRepository
import com.example.asaxiybookcompose.myLog
import com.example.asaxiybookcompose.presentation.screen.library.LibraryIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.File
import javax.inject.Inject

@HiltViewModel
class InfoViewModelImpl @Inject constructor(
    private val repo: BookRepository
) : ViewModel() {
    val book = MutableSharedFlow<File>()

    fun onEventDispatcherLibrary(intent: InfoViewModel) {
        when(intent) {
            is InfoViewModel.OpenBook -> {
                if (isDownload(intent.bookData)) {
                    repo.getBook(intent.bookData)
                        .onEach { result ->
                            result.onSuccess {
                                "model bor kitobni olish o;xshadi $it".myLog()
                                book.emit(it)
                            }
                        }
                        .launchIn(viewModelScope)
                }
                else {
                    downloadBook(intent.bookData)
                }
            }
        }
    }

     private fun downloadBook(bookUIData: BookUIData) {
        "repo download qilish kerak: ${bookUIData.bookUrl}".myLog()
        repo.downloadBookWithProgress(bookUIData)
            .onEach {
                when (it) {
                    is UploadData.Progress -> {
                        "model download progress ${it.uploadBytes}".myLog()
//                        progressPercentSharedFlow.tryEmit(it.uploadBytes)
                    }

                    is UploadData.Success -> {
                        "model download success".myLog()
                        repo.getBook(bookUIData)
                            .onEach { result ->
                                result.onSuccess {file ->
                                    "model bor kitobni olish o;xshadi $file".myLog()
                                    book.emit(file)
                                }
                            }
                            .launchIn(viewModelScope)
                    }

                    else -> {}
                }
            }
            .launchIn(viewModelScope)
    }

     private fun isDownload(book: BookUIData): Boolean {
        "model info bu kitob yuklab olinganmi".myLog()
        return repo.isDownload(book)
    }
}