package com.example.mydoctor.data.local

import android.arch.persistence.room.*
import com.example.mydoctor.model.Message

@Dao
interface MessageDAO {

    @Query("SELECT * FROM message")
    fun getMessage
                (): List<Message>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message: Message): Long

    @Update
    fun updateMessage(message: Message): Int

    @Delete
    fun deleteMessage(message: Message): Int
}