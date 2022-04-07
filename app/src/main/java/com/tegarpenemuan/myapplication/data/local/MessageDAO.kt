package com.tegarpenemuan.myapplication.data.local

import androidx.room.*

@Dao
interface MessageDAO {

    @Query("SELECT * FROM message")
    fun getMessage(): List<MessageEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(messageEntity: MessageEntity): Long

    @Update
    fun updateMessage(messageEntity: MessageEntity): Int

    @Delete
    fun deleteMessage(messageEntity: MessageEntity): Int
}