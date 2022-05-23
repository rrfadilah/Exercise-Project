package net.mzhasanah.fiveinone.exerciseproject.di

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.mzhasanah.fiveinone.exerciseproject.Constant
import net.mzhasanah.fiveinone.exerciseproject.data.local.MessageDAO
import net.mzhasanah.fiveinone.exerciseproject.data.local.UserDAO
import net.mzhasanah.fiveinone.exerciseproject.database.MyDoctorDatabase
import net.mzhasanah.fiveinone.exerciseproject.datastore.AuthDataStoreManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalStorageModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MyDoctorDatabase {
        return MyDoctorDatabase.getInstance(context = context)
    }

    @Singleton
    @Provides
    fun provideUserDao(db: MyDoctorDatabase): UserDAO {
        return db.userDAO()
    }

    @Singleton
    @Provides
    fun provideMessageDao(db: MyDoctorDatabase): MessageDAO {
        return db.messageDAO()
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            Constant.Preferences.PREF_NAME,
            AppCompatActivity.MODE_PRIVATE
        )
    }

    @Singleton
    @Provides
    fun provideAuthDataStoreManager(@ApplicationContext context: Context)
            : AuthDataStoreManager {
        return AuthDataStoreManager(context = context)
    }
}