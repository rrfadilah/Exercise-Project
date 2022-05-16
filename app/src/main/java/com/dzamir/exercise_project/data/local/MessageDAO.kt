package com.dzamir.exercise_project.data.local

import android.arch.persistence.room.*

@Dao
interface MessageDAO {
    @Query("SELECT * FROM message")
    fun getMessage(): List<MessageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message: MessageEntity): Long

    @Update
    fun updateMessage(message: MessageEntity): Int

    @Delete
    fun deleteMessage(message: MessageEntity): Int
}