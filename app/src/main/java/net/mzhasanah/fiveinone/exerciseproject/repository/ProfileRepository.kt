package net.mzhasanah.fiveinone.exerciseproject.repository

import net.mzhasanah.fiveinone.exerciseproject.common.Resource
import net.mzhasanah.fiveinone.exerciseproject.common.Status
import net.mzhasanah.fiveinone.exerciseproject.data.api.image.ImageAPI
import net.mzhasanah.fiveinone.exerciseproject.data.api.image.ImageDataResponse
import okhttp3.MultipartBody

class ProfileRepository(private val imageAPI: ImageAPI) {
    suspend fun uploadImage(image: MultipartBody.Part): Resource<ImageDataResponse> {
        imageAPI.uploadImage(image = image).let {
            if (it.isSuccessful) {
                return Resource(
                    status = Status.SUCCESS,
                    data = it.body(),
                    message = null
                )
            } else {
                return Resource(
                    status = Status.ERROR,
                    data = null,
                    message = it.errorBody().toString()
                )
            }
        }
    }
}