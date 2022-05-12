package com.rizky.exercise_project.repository

import com.rizky.exercise_project.common.Resource
import com.rizky.exercise_project.common.Status
import com.rizky.exercise_project.data.api.image.ImageAPI
import com.rizky.exercise_project.data.api.image.ImageDataResponse
import com.rizky.exercise_project.data.local.UserEntity
import com.rizky.exercise_project.database.MyDoctorDatabase
import com.rizky.exercise_project.datastore.CounterDataStoreManager
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

class ProfileRepository(
    private val imageAPI: ImageAPI,
    private val db: MyDoctorDatabase,
    private val prefDataStore: CounterDataStoreManager,
) {
    suspend fun uploadImage(image: MultipartBody.Part): Resource<ImageDataResponse> {
        imageAPI.uploadImage(image = image).let {
            if (it.isSuccessful) {
                updateImageProfile(it.body()?.data?.thumb?.url.orEmpty())
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

    suspend fun updateImageProfile(image: String): Long {
        val profile = db.userDAO().getUser()
        val updatedProfile = UserEntity(
            id = profile?.id.orEmpty(),
            email = profile?.email.orEmpty(),
            name = profile?.name.orEmpty(),
            job = profile?.job.orEmpty(),
            image = image
        )
        return db.userDAO().insertUser(updatedProfile)
    }

    suspend fun deleteProfile(): Int {
        val profile = db.userDAO().getUser()
        val deleteProfile = UserEntity(
            id = profile?.id.orEmpty(),
            email = profile?.email.orEmpty(),
            name = profile?.name.orEmpty(),
            job = profile?.job.orEmpty(),
            image = profile?.image.orEmpty()
        )
        return db.userDAO().deleteUser(deleteProfile)
    }

    suspend fun setCounter(value: Int) {
        prefDataStore.setCounter(value)
    }

    fun getCounter(): Flow<Int> {
        return prefDataStore.getCounter()
    }

    suspend fun increment() {
        prefDataStore.incrementCounter()
    }

    suspend fun decrement() {
        prefDataStore.decrementCounter()
    }
}
