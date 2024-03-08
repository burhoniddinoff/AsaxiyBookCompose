package com.example.asaxiybookcompose.presentation.screen.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asaxiybookcompose.data.data.BookUIData
import com.example.asaxiybookcompose.domain.AppRepository
import com.example.asaxiybookcompose.myLog
import com.example.asaxiybookcompose.navigation.AppNavigator
import com.example.asaxiybookcompose.presentation.screen.info.InfoScreen
import com.sudo_pacman.asaxiybooks.data.model.CategoryByBooksData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val repository: AppRepository,
    private val navigator: AppNavigator,
) : ViewModel() {
    val loadCategoryBookList = MutableSharedFlow<List<CategoryByBooksData>>()
    val errorMessage = MutableSharedFlow<String>()
    val progress = MutableSharedFlow<Boolean>()




    fun onEventDispatcherLibrary(intent: LibraryIntent) {
        when (intent) {
            LibraryIntent.GetAllCategoryList -> {
                "library vm kitob olishga keldi".myLog()
                progress.tryEmit(true)
                repository.getCategoryByPdfBooks().onEach { result ->
                    result.onSuccess {
                        progress.emit(false)
                        "library vm keldi ${it.size} ".myLog()
                        loadCategoryBookList.emit(it)
                    }
                    result.onFailure {
                        progress.emit(false)
                        errorMessage.emit(it.message.toString())
                    }
                }.launchIn(viewModelScope)

            }
            is LibraryIntent.BookClick -> {
                nextScreen(intent.product)
            }
        }
    }

    private fun nextScreen(product: BookUIData) {
        viewModelScope.launch {
            navigator.navigate(InfoScreen(product))
        }
    }
}