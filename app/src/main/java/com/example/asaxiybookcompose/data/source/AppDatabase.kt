package com.example.asaxiybookcompose.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.asaxiybookcompose.data.dao.BookDao
import com.example.asaxiybookcompose.data.entity.EntityBookData


@Database(entities = [EntityBookData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getBookDao(): BookDao
}


