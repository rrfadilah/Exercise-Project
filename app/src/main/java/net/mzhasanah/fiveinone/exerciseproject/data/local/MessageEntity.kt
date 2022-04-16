package net.mzhasanah.fiveinone.exerciseproject.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "message")
@Parcelize
data class MessageEntity(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "image") var image: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "message") var message: String
) : Parcelable