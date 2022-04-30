package com.rizky.exercise_project.data.api.image

import com.rizky.exercise_project.network.ImageApiClient
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

/**
 * com.rizky.exercise_project.data.api.image
 *
 * Created by Rizky Fadilah on 30/04/22.
 * https://github.com/rizkyfadilah
 *
 */

interface ImageAPI {
    @POST("upload") // RequestBody
    @Multipart
    suspend fun uploadImage(
        @Query("expiration") expiration: Int = 10000,
        @Query("key") key: String = ImageApiClient.APIKEY,
        @Part image: MultipartBody.Part
    ): Response<ImageDataResponse>
}