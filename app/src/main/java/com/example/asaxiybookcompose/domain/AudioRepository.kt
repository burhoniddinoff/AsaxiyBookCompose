package com.example.asaxiybookcompose.domain

import kotlinx.coroutines.flow.Flow
import java.io.File

interface AudioRepository {
    fun downloadAudioWithProgress(data: String): Flow<File>
}