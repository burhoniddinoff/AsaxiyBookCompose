package com.example.asaxiybookcompose.presentation.screen.audio_item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.asaxiybookcompose.navigation.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioItemVM @Inject constructor(
    private val navigator: AppNavigator,
) : ViewModel() {

    fun onEventDispatcher(intent: AudioItemIntent) {
        when (intent) {
            AudioItemIntent.OnClickBack -> {
                backScreen()
            }
        }
    }

    private fun backScreen() {
        viewModelScope.launch {
            navigator.back()
        }
    }

}