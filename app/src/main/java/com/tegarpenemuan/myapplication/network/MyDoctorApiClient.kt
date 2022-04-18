package com.tegarpenemuan.myapplication.network

import com.google.gson.GsonBuilder
import com.tegarpenemuan.myapplication.data.api.MessageAPI
import com.tegarpenemuan.myapplication.data.api.auth.AuthAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MyDoctorApiClient {
    // BASE_URL merupakan URL default untuk mengkoneksikan aplikasi dengan endpoint pada API
//    const val BASE_URL = "https://private-82636-mydoctorexample.apiary-mock.com/api/"
    const val BASE_URL = "http://drivingrake.backendless.app/api/"

    private val logging: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    //    val instanceMessage: MessageAPI by lazy {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//
//        retrofit.create(MessageAPI::class.java)

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val instanceMessage: MessageAPI by lazy {
        retrofit.create(MessageAPI::class.java)
    }

    val instanceSignIn: AuthAPI by lazy {
        retrofit.create(AuthAPI::class.java)
    }
}