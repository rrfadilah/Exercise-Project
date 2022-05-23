package net.mzhasanah.fiveinone.exerciseproject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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

}