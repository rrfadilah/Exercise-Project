package com.dzamir.exercise_project.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.dzamir.exercise_project.data.local.MessageDAO
import com.dzamir.exercise_project.data.local.MessageEntity
import com.dzamir.exercise_project.data.local.UserDAO
import com.dzamir.exercise_project.data.local.UserEntity

@Database(entities = [MessageEntity::class, UserEntity::class], version = 3)
abstract class MyDoctorDatabase: RoomDatabase() {
    abstract fun messageDAO() : MessageDAO
    abstract fun userDAO() : UserDAO

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