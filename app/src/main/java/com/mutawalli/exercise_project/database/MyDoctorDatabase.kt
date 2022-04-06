package com.mutawalli.exercise_project.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.mutawalli.exercise_project.model.Message


@Database(entities = [Message::class], version = 1)
abstract class MyDoctorDatabase : RoomDatabase() {
    companion object {
        private var INSTANCE: MyDoctorDatabase? = null
        private const val DB_NAME = "MyDoctor.db"

        fun getInstance(context: Context): MyDoctorDatabase {
            if (INSTANCE == null) {
                synchronized(MyDoctorDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MyDoctorDatabase::class.java,
                        DB_NAME
                    ).build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}