package com.example.asaxiybookcompose

import android.util.Log
import com.example.asaxiybookcompose.data.data.BookUIData
import com.example.asaxiybookcompose.data.data.UserData
import com.example.asaxiybookcompose.data.entity.EntityBookData
import com.google.firebase.firestore.QuerySnapshot

fun String.myLog(tag: String = "TTT") = Log.d(tag, this)


object Mapper {
    fun QuerySnapshot.toUserDataList(): List<UserData> {
        val userList = mutableListOf<UserData>()
        for (document in this) {
            val id = document.id
            val name = document.data.getOrDefault("name", "").toString()
            val gmail = document.data.getOrDefault("gmail", "").toString()
            val password = document.data.getOrDefault("password", "").toString()
            val booksId = document.data.getOrDefault("booksId", mutableListOf<String>()) as List<String>
            "mapper user idlar ${booksId.joinToString(",")}".myLog()

            val userData = UserData(
                id = id,
                name = name,
                gmail = gmail,
                password = password,
//                booksId = booksId.toMutableList() as ArrayList<String>
            )
            userList.add(userData)
        }
        return userList
    }
}

fun EntityBookData.toUiData() : BookUIData {
    return BookUIData(
        docID = this.docID,
        audioUrl = this.audioUrl,
        author = this.author,
        bookUrl = this.bookUrl,
        categoryId = this.categoryId,
        coverImage = this.coverImage,
        description = this.description,
        filePath = this.filePath,
        name = this.name,
        totalSize = this.totalSize,
        type = this.type
    )
}