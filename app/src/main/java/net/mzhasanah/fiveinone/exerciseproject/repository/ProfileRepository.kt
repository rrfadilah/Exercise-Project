package net.mzhasanah.fiveinone.exerciseproject.repository

import net.mzhasanah.fiveinone.exerciseproject.common.Resource
import net.mzhasanah.fiveinone.exerciseproject.common.Status
import net.mzhasanah.fiveinone.exerciseproject.data.api.image.ImageAPI
import net.mzhasanah.fiveinone.exerciseproject.data.api.image.ImageDataResponse
import okhttp3.MultipartBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.mzhasanah.fiveinone.exerciseproject.database.MyDoctorDatabase
import net.mzhasanah.fiveinone.exerciseproject.model.ProfileModel

class ProfileRepository(private val imageAPI: ImageAPI, private val db: MyDoctorDatabase) {
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

    suspend fun getProfile(): ProfileModel {
        return db.userDAO().getUser().let {
            ProfileModel(
                id = it?.id.orEmpty(),
                name = it?.name.orEmpty(),
                job = it?.job.orEmpty(),
                image = it?.image.orEmpty()
            )
        }
    }
}