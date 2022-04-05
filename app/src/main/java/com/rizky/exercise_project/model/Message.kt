package com.rizky.exercise_project.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * com.rizky.exercise_project.model
 *
 * Created by Rizky Fadilah on 05/04/22.
 * https://github.com/rizkyfadilah
 *
 */

@Entity(tableName = "message")
@Parcelize
data class Message(
    @PrimaryKey(autoGenerate = true) var id: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "message") var message: String
) : Parcelable
