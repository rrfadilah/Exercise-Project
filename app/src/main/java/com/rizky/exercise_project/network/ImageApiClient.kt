package com.rizky.exercise_project.network

import com.rizky.exercise_project.data.api.image.ImageAPI
import com.rizky.exercise_project.data.api.tmdb.TMDBAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * com.rizky.exercise_project.network
 *
 * Created by Rizky Fadilah on 23/04/22.
 * https://github.com/rizkyfadilah
 *
 */

object ImageApiClient {
    const val BASE_URL = "https://api.imgbb.com/1/"
    const val APIKEY = "56e074fb9f11e246bde93fecb8ba5204"

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

    val instanceImage: ImageAPI by lazy {
        retrofit.create(ImageAPI::class.java)
    }

}
