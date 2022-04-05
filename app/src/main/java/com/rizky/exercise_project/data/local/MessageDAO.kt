package com.rizky.exercise_project.data.local

import android.arch.persistence.room.*
import com.rizky.exercise_project.model.Message

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
    fun getMessage(): List<Message>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message: Message): Long

    @Update
    fun updateMessage(message: Message): Int

    @Delete
    fun deleteMessage(message: Message): Int
}
