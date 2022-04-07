package com.example.mydoctor.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mydoctor.data.local.MessageDAO
import com.example.mydoctor.data.local.MessageEntity

@Database(entities = [MessageEntity::class], version = 1)
abstract class MyDoctorDatabase : RoomDatabase() {
    companion object {
        private var INSTANCE: MyDoctorDatabase? = null
        private const val DB_NAME = "MyDoctor.db"

        fun getInstance(context: Context): MyDoctorDatabase {
            if (INSTANCE == null) {
                synchronized(MyDoctorDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MyDoctorDatabase::class.java, DB_NAME
                    ).build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    abstract fun messageDAO(): MessageDAO
}