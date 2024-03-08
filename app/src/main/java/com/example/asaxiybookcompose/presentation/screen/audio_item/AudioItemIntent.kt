package com.example.asaxiybookcompose.presentation.screen.audio_item

import com.example.asaxiybookcompose.data.data.BookUIData

interface AudioItemIntent {
    data object OnClickBack : AudioItemIntent

    data class OpenAudio(val bookUIData: BookUIData) : AudioItemIntent
}