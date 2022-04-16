package net.mzhasanah.fiveinone.exerciseproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.mzhasanah.fiveinone.exerciseproject.data.local.MessageDAO
import net.mzhasanah.fiveinone.exerciseproject.data.local.MessageEntity
import net.mzhasanah.fiveinone.exerciseproject.data.local.UserDAO
import net.mzhasanah.fiveinone.exerciseproject.data.local.UserEntity

@Database(entities = [MessageEntity::class, UserEntity::class], version = 2)
abstract class MyDoctorDatabase : RoomDatabase() {
    abstract fun messageDAO(): MessageDAO
    abstract fun userDAO(): UserDAO

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
