package com.rizky.exercise_project.di

import android.content.Context
import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.data.api.auth.AuthAPI
import com.rizky.exercise_project.data.api.image.ImageAPI
import com.rizky.exercise_project.data.local.UserDAO
import com.rizky.exercise_project.datastore.AuthDataStoreManager
import com.rizky.exercise_project.datastore.CounterDataStoreManager
import com.rizky.exercise_project.repository.AuthRepository
import com.rizky.exercise_project.repository.ProfileRepository
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

    @Singleton
    @Provides
    fun provideProfileRepository(
        imageAPI: ImageAPI,
        authAPI: AuthAPI,
        userDAO: UserDAO,
        @Named(Constant.Named.APIKEY_IMAGE) apikey: String,
        counterDataStoreManager: CounterDataStoreManager
    ): ProfileRepository {
        return ProfileRepository(
            imageAPI = imageAPI,
            authAPI = authAPI,
            dao = userDAO,
            apiKey = apikey,
            prefDataStore = counterDataStoreManager
        )
    }
}
