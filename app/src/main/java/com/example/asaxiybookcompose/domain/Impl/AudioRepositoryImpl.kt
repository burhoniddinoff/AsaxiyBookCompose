package com.example.asaxiybookcompose.domain.Impl

import com.example.asaxiybookcompose.domain.AudioRepository
import com.example.asaxiybookcompose.myLog
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import javax.inject.Inject

class AudioRepositoryImpl @Inject constructor() : AudioRepository {
    private val fireStorage = Firebase.storage
    private var loadTask: FileDownloadTask? = null

    override fun downloadAudioWithProgress(data: String): Flow<File> = callbackFlow {
        "repo yuklab olish kerak $data".myLog()
        "repo yuklab olish kerak bo'lkan audio url ${data}".myLog()
        val audio = File.createTempFile("mp3", ".mp3")
        loadTask = fireStorage
            .getReferenceFromUrl(data)
            .getFile(audio)

        loadTask!!
            .addOnSuccessListener {
                trySend(File(audio.absolutePath))
            }


        awaitClose()
    }
        .flowOn(Dispatchers.IO)

}