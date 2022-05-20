package com.rizky.exercise_project.di

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.data.local.MessageDAO
import com.rizky.exercise_project.data.local.UserDAO
import com.rizky.exercise_project.database.MyDoctorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * com.rizky.exercise_project.di
 *
 * Created by Rizky Fadilah on 20/05/22.
 * https://github.com/rizkyfadilah
 *
 */

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
}
