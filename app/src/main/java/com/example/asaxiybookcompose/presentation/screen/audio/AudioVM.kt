package com.example.asaxiybookcompose.presentation.screen.audio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asaxiybookcompose.domain.AppRepository
import com.example.asaxiybookcompose.navigation.AppNavigator
import com.example.asaxiybookcompose.presentation.screen.audio_item.AudioItemScreen
import com.sudo_pacman.asaxiybooks.data.model.CategoryByBooksData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AudioVM @Inject constructor(
    private val repository: AppRepository,
    private val navigator: AppNavigator,
) : ViewModel() {
    val loadCategoryBookList = MutableSharedFlow<List<CategoryByBooksData>>()
    val errorMessage = MutableSharedFlow<String>()
    val progress = MutableSharedFlow<Boolean>()

    fun onEventDispatcherLibrary(intent: AudioIntent) {
        when (intent) {
            AudioIntent.GetAllCategoryList -> {
                progress.tryEmit(true)
                repository.getCategoryByAudioBooks().onEach { result ->
                    result.onSuccess {
                        progress.emit(false)
                        loadCategoryBookList.emit(it)
                    }
                    result.onFailure {
                        progress.emit(false)
                        errorMessage.emit(it.message.toString())
                    }
                }.launchIn(viewModelScope)

            }
        }
    }

    fun nextScreen() {
        viewModelScope.launch {
            navigator.navigate(AudioItemScreen())
        }
    }

}