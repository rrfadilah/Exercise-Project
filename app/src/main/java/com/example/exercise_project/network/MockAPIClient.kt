package com.example.exercise_project.network

import com.example.exercise_project.data.API.Auth.AuthAPI
import com.example.exercise_project.data.API.Auth.Home.HomeAPI
import com.example.exercise_project.data.API.MessageAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MockAPIClient {
    // BASE_URL merupakan URL default untuk mengkoneksikan aplikasi dengan endpoint pada API
    const val BASE_URL = "https://private-anon-36a02d1bf5-mydoctorexample.apiary-mock.com/api/"

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

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val instanceHome: HomeAPI by lazy {
        retrofit.create(HomeAPI::class.java)
    }
}