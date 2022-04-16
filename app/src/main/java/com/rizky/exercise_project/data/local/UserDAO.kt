package com.rizky.exercise_project.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * com.rizky.exercise_project.data.local
 *
 * Created by Rizky Fadilah on 07/04/22.
 * https://github.com/rizkyfadilah
 *
 */

@Dao
interface UserDAO {
    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userEntity: UserEntity): Long
}
