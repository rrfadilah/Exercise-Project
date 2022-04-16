package com.rizky.exercise_project.data.local

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
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "job") val job: String,
    @ColumnInfo(name = "image") val image: String,
)
