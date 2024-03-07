package com.example.asaxiybookcompose.data.source

import android.content.Context
import android.content.SharedPreferences
import com.example.asaxiybookcompose.data.data.UserData

object MySharedPreference {

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("Uzum_Market", Context.MODE_PRIVATE)
    }

    fun setUserData(userData: UserData) {
        sharedPreferences.edit().putString("id", userData.id).apply()
        sharedPreferences.edit().putString("name", userData.name).apply()
        sharedPreferences.edit().putString("gmail", userData.gmail).apply()
        sharedPreferences.edit().putString("password", userData.password).apply()

//        for (index in userData.booksId.indices) {
//            sharedPreferences.edit().putString("book_id$index", userData.booksId[index]).apply()
//        }

//        sharedPreferences.edit().putInt("books_id_size", userData.booksId.size).apply()

        sharedPreferences.edit().putBoolean("login", true).apply()
    }

    fun getUserData(): UserData {
        val id = sharedPreferences.getString("id", "") ?: ""
        val name = sharedPreferences.getString("name", "") ?: ""
        val gmail = sharedPreferences.getString("gmail", "") ?: ""
        val password = sharedPreferences.getString("password", "") ?: ""

        val booksIdSize = sharedPreferences.getInt("books_id_size", 0)

        val booksId = ArrayList<String>()

        for (i in 0 until booksIdSize) {
            booksId.add(sharedPreferences.getString("book_id$i", "") ?: "")
        }

        return UserData(
            id = id, name = name, gmail = gmail, password = password
//            , booksId = booksId
        )
    }

    fun getBookLink(bookId: String): String {
        return sharedPreferences.getString(bookId, "") ?: ""
    }

    fun setBookInfo(bookId: String, bookLink: String) {
        sharedPreferences.edit().putString(bookId, bookLink).apply()
    }

    fun isLogin(): Boolean = sharedPreferences.getBoolean("login", false)

    fun logOut() {
        sharedPreferences.edit().putString("name", "").apply()
        sharedPreferences.edit().putString("gmail", "").apply()
        sharedPreferences.edit().putString("password", "").apply()
        sharedPreferences.edit().putString("type", "").apply()
        sharedPreferences.edit().putBoolean("login", false).apply()
    }


}