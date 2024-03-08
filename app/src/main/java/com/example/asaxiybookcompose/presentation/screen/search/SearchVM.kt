package com.example.asaxiybookcompose.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asaxiybookcompose.data.data.BookUIData
import com.example.asaxiybookcompose.domain.AppRepository
import com.example.asaxiybookcompose.navigation.AppNavigator
import com.example.asaxiybookcompose.presentation.screen.info.InfoScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchVM @Inject constructor(
    val navigator: AppNavigator,
    private val repository: AppRepository,
) : ViewModel() {

    val resultBooks = MutableStateFlow<List<BookUIData>>(arrayListOf())

    fun evenDispatcher(intent: SearchIntent) {

        when (intent) {

            SearchIntent.BackScreen -> {
                backScreen()
            }

           is SearchIntent.NextScreen -> {
                nextScreen(intent.data)
            }

            is SearchIntent.ResultSearchBook -> {
                repository.getBooksByName(intent.text)
                    .onEach {
                        it.onSuccess { list ->
                            resultBooks.value = list
                        }.onFailure {

                        }
                    }.launchIn(viewModelScope)
            }


        }

    }

    private fun backScreen() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    private fun nextScreen(data: BookUIData) {
        viewModelScope.launch {
            navigator.navigate(InfoScreen(data))
        }
    }

}