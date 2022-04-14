package com.muhira.exercise_project.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * com.rizky.exercise_project.data.local
 *
 * Created by Rizky Fadilah on 07/04/22.
 * https://github.com/rizkyfadilah
 *
 */

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,
)
