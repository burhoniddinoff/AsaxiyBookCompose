package com.example.asaxiybookcompose.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.asaxiybookcompose.data.entity.EntityBookData

@Dao
interface BookDao {
    @Query("SELECT * FROM entitybookdata")
    fun getAllBooks(): List<EntityBookData>

    @Query("SELECT * FROM entitybookdata where id=:id")
    fun getBooksById(id: Long): EntityBookData

    @Insert
    fun setBook(bookData: EntityBookData)

    @Query("Select id From entitybookdata Where bookUrl = :url")
    fun isHas(url: String): Long
}