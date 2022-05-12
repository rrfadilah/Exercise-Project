package com.rizky.exercise_project.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

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

    @Delete
    suspend fun deleteUser(userEntity: UserEntity): Int
}
