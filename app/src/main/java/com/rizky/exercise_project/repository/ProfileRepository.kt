package com.rizky.exercise_project.repository

import com.rizky.exercise_project.common.Resource
import com.rizky.exercise_project.common.Status
import com.rizky.exercise_project.data.api.image.ImageAPI
import com.rizky.exercise_project.data.api.image.ImageDataResponse
import com.rizky.exercise_project.data.local.UserEntity
import com.rizky.exercise_project.database.MyDoctorDatabase
import com.rizky.exercise_project.model.ProfileModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.MultipartBody

/**
 * com.rizky.exercise_project.repository
 *
 * Created by Rizky Fadilah on 30/04/22.
 * https://github.com/rizkyfadilah
 *
 */

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
