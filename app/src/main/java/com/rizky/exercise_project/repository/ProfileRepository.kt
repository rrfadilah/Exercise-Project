package com.rizky.exercise_project.repository

import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.common.Resource
import com.rizky.exercise_project.common.Status
import com.rizky.exercise_project.data.api.auth.AuthAPI
import com.rizky.exercise_project.data.api.auth.SignInResponse
import com.rizky.exercise_project.data.api.auth.UpdateProfileRequest
import com.rizky.exercise_project.data.api.image.ImageAPI
import com.rizky.exercise_project.data.api.image.ImageDataResponse
import com.rizky.exercise_project.data.local.UserDAO
import com.rizky.exercise_project.data.local.UserEntity
import com.rizky.exercise_project.database.MyDoctorDatabase
import com.rizky.exercise_project.datastore.CounterDataStoreManager
import com.rizky.exercise_project.model.ProfileModel
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Named

/**
 * com.rizky.exercise_project.repository
 *
 * Created by Rizky Fadilah on 30/04/22.
 * https://github.com/rizkyfadilah
 *
 */

class ProfileRepository @Inject constructor(
    private val imageAPI: ImageAPI,
    private val authAPI: AuthAPI,
    private val dao: UserDAO,
    private val prefDataStore: CounterDataStoreManager,
    @Named(Constant.Named.APIKEY_IMAGE) private val apiKey: String
) {
    suspend fun uploadImage(image: MultipartBody.Part): Resource<ImageDataResponse> {
        imageAPI.uploadImage(
            image = image,
            key = apiKey
        ).let {
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

    suspend fun updateProfile(image: String): Resource<SignInResponse> {
        val profile = getProfile()
        val request = UpdateProfileRequest(
            name = profile.name,
            image = image,
            job = profile.job
        )

        return authAPI.updateProfile(
            id = profile.id,
            request = request
        ).let {
            if (it.isSuccessful) {
                Resource(
                    status = Status.SUCCESS,
                    data = it.body(),
                    message = null
                )
            } else {
                Resource(
                    status = Status.ERROR,
                    data = null,
                    message = it.errorBody().toString()
                )
            }
        }
    }

    suspend fun getProfile(): ProfileModel {
        return dao.getUser().let {
            ProfileModel(
                id = it?.id.orEmpty(),
                name = it?.name.orEmpty(),
                job = it?.job.orEmpty(),
                image = it?.image.orEmpty()
            )
        }
    }

    suspend fun updateImageProfile(image: String): Long {
        val profile = dao.getUser()
        val updatedProfile = UserEntity(
            id = profile?.id.orEmpty(),
            email = profile?.email.orEmpty(),
            name = profile?.name.orEmpty(),
            job = profile?.job.orEmpty(),
            image = image
        )
        return dao.insertUser(updatedProfile)
    }

    suspend fun deleteProfile(): Int {
        val profile = dao.getUser()
        val deleteProfile = UserEntity(
            id = profile?.id.orEmpty(),
            email = profile?.email.orEmpty(),
            name = profile?.name.orEmpty(),
            job = profile?.job.orEmpty(),
            image = profile?.image.orEmpty()
        )
        return dao.deleteUser(deleteProfile)
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
