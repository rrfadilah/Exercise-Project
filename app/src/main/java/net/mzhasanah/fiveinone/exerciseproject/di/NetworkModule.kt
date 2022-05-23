package net.mzhasanah.fiveinone.exerciseproject.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.mzhasanah.fiveinone.exerciseproject.BuildConfig
import net.mzhasanah.fiveinone.exerciseproject.Constant
import net.mzhasanah.fiveinone.exerciseproject.data.api.MessageAPI
import net.mzhasanah.fiveinone.exerciseproject.data.api.auth.AuthAPI
import net.mzhasanah.fiveinone.exerciseproject.data.api.home.HomeAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    @Named(Constant.Named.BASE_URL_MYDOCTOR)
    fun provideBaseUrlMyDoctor(): String = "http://drivingrake.backendless.app/api/"

    @Singleton
    @Provides
    @Named(Constant.Named.BASE_URL_MOCK)
    fun provideBaseUrlMock(): String = "http://private-82636-mydoctorexample.apiary-mock.com/api/"

    @Singleton
    @Provides
    @Named(Constant.Named.BASE_URL_FLAVOR)
    fun provideBaseUrl(
        @Named(Constant.Named.BASE_URL_MOCK) baseUrlMock: String,
        @Named(Constant.Named.BASE_URL_MYDOCTOR) baseUrlMyDoctor: String
    ): String = if (BuildConfig.FLAVOR == "ayam_panggang") baseUrlMock else baseUrlMyDoctor

    @Singleton
    @Provides
    fun provideHttpLogging(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    @Named(Constant.Named.RETROFIT_MYDOCTOR)
    fun provideRetrofitMyDoctor(
        @Named(Constant.Named.BASE_URL_FLAVOR) baseUrl: String,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideHomeAPI(
        @Named(Constant.Named.RETROFIT_MYDOCTOR) retrofit: Retrofit
    ): HomeAPI {
        return retrofit.create(HomeAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthAPI(
        @Named(Constant.Named.RETROFIT_MYDOCTOR) retrofit: Retrofit
    ): AuthAPI {
        return retrofit.create(AuthAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideMessageAPI(
        @Named(Constant.Named.RETROFIT_MYDOCTOR) retrofit: Retrofit
    ): MessageAPI {
        return retrofit.create(MessageAPI::class.java)
    }
}