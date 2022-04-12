package com.rizky.exercise_project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rizky.exercise_project.data.local.MessageDAO
import com.rizky.exercise_project.data.local.MessageEntity

@Database(entities = [MessageEntity::class], version = 1)
abstract class MyDoctorDatabase : RoomDatabase() {
    abstract fun messageDAO(): MessageDAO

    companion object {
        private const val DB_NAME = "MyDoctor.db"

        @Volatile
        private var INSTANCE: MyDoctorDatabase? = null

        fun getInstance(context: Context): MyDoctorDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): MyDoctorDatabase {
            return Room.databaseBuilder(context, MyDoctorDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}