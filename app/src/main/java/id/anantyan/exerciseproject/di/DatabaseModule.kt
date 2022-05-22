package id.anantyan.exerciseproject.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.anantyan.exerciseproject.data.local.MessagesDao
import id.anantyan.exerciseproject.data.local.UsersDao
import id.anantyan.exerciseproject.database.RoomDB
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun messagesDao(roomDB: RoomDB): MessagesDao {
        return roomDB.messagesDao()
    }

    @Provides
    fun usersDao(roomDB: RoomDB): UsersDao {
        return roomDB.usersDao()
    }

    @Singleton
    @Provides
    fun providerDatabase(
        @ApplicationContext context: Context
    ): RoomDB {
        return Room.databaseBuilder(
            context.applicationContext,
            RoomDB::class.java,
            "db_users"
        ).fallbackToDestructiveMigration().build()
    }
}