package net.mzhasanah.fiveinone.exerciseproject.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.mzhasanah.fiveinone.exerciseproject.Constant
import net.mzhasanah.fiveinone.exerciseproject.data.api.auth.AuthAPI
import net.mzhasanah.fiveinone.exerciseproject.data.api.image.ImageAPI
import net.mzhasanah.fiveinone.exerciseproject.data.local.UserDAO
import net.mzhasanah.fiveinone.exerciseproject.datastore.AuthDataStoreManager
import net.mzhasanah.fiveinone.exerciseproject.datastore.CounterDataStoreManager
import net.mzhasanah.fiveinone.exerciseproject.repository.AuthRepository
import net.mzhasanah.fiveinone.exerciseproject.repository.ProfileRepository
import javax.inject.Named
import javax.inject.Singleton

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