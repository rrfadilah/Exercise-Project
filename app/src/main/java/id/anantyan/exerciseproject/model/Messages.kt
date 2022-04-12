package id.anantyan.exerciseproject.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "table_messages")
@Parcelize
data class Messages(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("image")
    @ColumnInfo(name = "image")
    val image: String? = null,
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String? = null,
    @SerializedName("message")
    @ColumnInfo(name = "message")
    val message: String? = null
) : Parcelable

@Parcelize
data class MessagesList(
    @SerializedName("message")
    val message: List<Messages>? = null
) : Parcelable
