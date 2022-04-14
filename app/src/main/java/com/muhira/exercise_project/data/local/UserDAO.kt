package com.muhira.exercise_project.data.local

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
    @Query("SELECT * FROM user WHERE email=:email AND password=:password LIMIT 1")
    fun getUser(email: String, password: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity): Long
}
