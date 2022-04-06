package com.mutawalli.exercise_project.data.local

import android.arch.persistence.room.*
import com.mutawalli.exercise_project.model.Message


@Dao
interface MessageDAO {

    @Query("SELECT * FROM message")
    fun getMessage(): List<Message>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message: Message): Long

    @Update
    fun updateMessage(message: Message): Int

    @Delete
    fun deleteMessage(message: Message): Int
}