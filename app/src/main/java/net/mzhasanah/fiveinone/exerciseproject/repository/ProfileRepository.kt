package net.mzhasanah.fiveinone.exerciseproject.repository

import net.mzhasanah.fiveinone.exerciseproject.common.Resource
import net.mzhasanah.fiveinone.exerciseproject.common.Status
import net.mzhasanah.fiveinone.exerciseproject.data.api.image.ImageAPI
import net.mzhasanah.fiveinone.exerciseproject.data.api.image.ImageDataResponse
import okhttp3.MultipartBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import net.mzhasanah.fiveinone.exerciseproject.data.api.auth.AuthAPI
import net.mzhasanah.fiveinone.exerciseproject.data.api.auth.SignInResponse
import net.mzhasanah.fiveinone.exerciseproject.data.api.auth.UpdateProfileRequest
import net.mzhasanah.fiveinone.exerciseproject.data.local.UserEntity
import net.mzhasanah.fiveinone.exerciseproject.database.MyDoctorDatabase
import net.mzhasanah.fiveinone.exerciseproject.datastore.CounterDataStoreManager
import net.mzhasanah.fiveinone.exerciseproject.model.ProfileModel

class ProfileRepository(
    private val imageAPI: ImageAPI,
    private val authAPI: AuthAPI,
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
        return db.userDAO().getUser().let {
            ProfileModel(
                id = it?.id.orEmpty(),
                name = it?.name.orEmpty(),
                job = it?.job.orEmpty(),
                image = it?.image.orEmpty()
            )
        }
    }

    suspend fun updateImageProfile(image:String): Long {
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