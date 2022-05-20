package com.rizky.exercise_project.di

import android.content.Context
import com.rizky.exercise_project.data.api.auth.AuthAPI
import com.rizky.exercise_project.data.local.UserDAO
import com.rizky.exercise_project.datastore.AuthDataStoreManager
import com.rizky.exercise_project.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/**
 * com.rizky.exercise_project.di
 *
 * Created by Rizky Fadilah on 19/05/22.
 * https://github.com/rizkyfadilah
 *
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideTestString(): String = "Testing string dengan hilt"

    @Singleton
    @Provides
    @Named("TestString2")
    fun provideTestString2(): String = "Testing string 2 dengan hilt"

    @Singleton
    @Provides
    fun provideAuthRepository(
        authDataStoreManager: AuthDataStoreManager,
        api: AuthAPI,
        dao: UserDAO
    ): AuthRepository {
        return AuthRepository(
            authDataStore = authDataStoreManager,
            api = api,
            dao = dao
        )
    }
}
