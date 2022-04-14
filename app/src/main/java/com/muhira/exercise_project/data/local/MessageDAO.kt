package com.muhira.exercise_project.data.local

import androidx.room.*

/**
 * com.rizky.exercise_project.data
 *
 * Created by Rizky Fadilah on 05/04/22.
 * https://github.com/rizkyfadilah
 *
 */

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
