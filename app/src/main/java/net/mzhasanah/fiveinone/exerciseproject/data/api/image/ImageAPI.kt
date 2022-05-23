package net.mzhasanah.fiveinone.exerciseproject.data.api.image

import net.mzhasanah.fiveinone.exerciseproject.network.ImageApiClient
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ImageAPI {
    @POST("upload") // RequestBody
    @Multipart
    suspend fun uploadImage(
        @Query("expiration") expiration: Int = 10000,
        @Query("key") key: String = ImageApiClient.APIKEY,
        @Part image: MultipartBody.Part
    ): Response<ImageDataResponse>
}