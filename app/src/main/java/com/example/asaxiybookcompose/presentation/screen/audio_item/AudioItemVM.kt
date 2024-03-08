package com.example.asaxiybookcompose.presentation.screen.audio_item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asaxiybookcompose.data.data.BookUIData
import com.example.asaxiybookcompose.domain.AudioRepository
import com.example.asaxiybookcompose.myLog
import com.example.asaxiybookcompose.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AudioItemVM @Inject constructor(
    private val navigator: AppNavigator,
    private val repo: AudioRepository
) : ViewModel() {

    val audio = MutableSharedFlow<File>()

    fun onEventDispatcher(intent: AudioItemIntent) {
        when (intent) {
            AudioItemIntent.OnClickBack -> {
                backScreen()
            }

            is AudioItemIntent.OpenAudio -> {
                downloadBook(intent.bookUIData)
            }
        }
    }

    private fun backScreen() {
        viewModelScope.launch {
            navigator.back()
        }
    }


    private fun downloadBook(bookUIData: BookUIData) {
        "repo download qilish kerak bo'lgan audio: ${bookUIData.bookUrl}".myLog()
        repo.downloadAudioWithProgress(bookUIData.bookUrl)
            .onEach {file ->
                audio.emit(file)
            }
            .launchIn(viewModelScope)
    }

}