package id.anantyan.exerciseproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.anantyan.exerciseproject.data.local.MessagesDao
import id.anantyan.exerciseproject.data.local.UsersDao
import id.anantyan.exerciseproject.model.Messages
import id.anantyan.exerciseproject.model.Users

@Database(entities = [
    Messages::class,
    Users::class
], version = 3, exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun messagesDao(): MessagesDao
    abstract fun usersDao(): UsersDao
}