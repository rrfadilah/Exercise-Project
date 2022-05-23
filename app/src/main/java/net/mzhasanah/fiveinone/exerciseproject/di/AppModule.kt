package net.mzhasanah.fiveinone.exerciseproject.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.mzhasanah.fiveinone.exerciseproject.datastore.AuthDataStoreManager
import net.mzhasanah.fiveinone.exerciseproject.repository.AuthRepository
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
    fun provideAuthDataStoreManager(@ApplicationContext context: Context)
            : AuthDataStoreManager {
        return AuthDataStoreManager(context = context)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(authDataStoreManager: AuthDataStoreManager)
            : AuthRepository {
        return AuthRepository(authDataStore = authDataStoreManager)
    }
}