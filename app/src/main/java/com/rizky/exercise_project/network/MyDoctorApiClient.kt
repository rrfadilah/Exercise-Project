package com.rizky.exercise_project.network

import com.rizky.exercise_project.data.api.auth.AuthAPI
import com.rizky.exercise_project.data.api.MessageAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * com.rizky.exercise_project.network
 *
 * Created by Rizky Fadilah on 12/04/22.
 * https://github.com/rizkyfadilah
 *
 */

object MyDoctorApiClient {
    // BASE_URL merupakan URL default untuk mengkoneksikan aplikasi dengan endpoint pada API
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
