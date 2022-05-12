package com.rizky.exercise_project.ui.profile

import androidx.lifecycle.*
import com.google.gson.Gson
import com.rizky.exercise_project.common.Status
import com.rizky.exercise_project.data.api.ErrorResponse
import com.rizky.exercise_project.database.MyDoctorDatabase
import com.rizky.exercise_project.model.ProfileModel
import com.rizky.exercise_project.repository.ProfileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.ResponseBody

/**
 * com.rizky.exercise_project.ui.profile
 *
 * Created by Rizky Fadilah on 30/04/22.
 * https://github.com/rizkyfadilah
 *
 */

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    val shouldShowImage: MutableLiveData<String> = MutableLiveData()
    val shouldShowError: MutableLiveData<String> = MutableLiveData()
    val shouldShowLoading: MutableLiveData<Boolean> = MutableLiveData()
    val shouldShowProfile: MutableLiveData<ProfileModel> = MutableLiveData()
    var shouldShowCounter: LiveData<Int> = repository.getCounter().asLiveData()

    fun onViewLoaded() {
        getProfile()
    }

    fun uploadImage(body: MultipartBody.Part) {
        shouldShowLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val result = repository.uploadImage(body)
            withContext(Dispatchers.Main) {
                when (result.status) {
                    Status.SUCCESS -> {
                        shouldShowImage.postValue(result.data?.data?.thumb?.url)
                        updateProfile(result.data?.data?.thumb?.url.orEmpty())
                    }
                    Status.ERROR -> {
                        shouldShowError.postValue(result.message.orEmpty())
                        shouldShowLoading.postValue(false)
                    }
                    Status.LOADING -> {

                    }
                }
            }
        }
    }

    private fun updateProfile(image: String) {
        viewModelScope.launch {
            val result = repository.updateProfile(image)
            withContext(Dispatchers.Main) {
                when (result.status) {
                    Status.SUCCESS -> {
                    }
                    Status.ERROR -> {
                        shouldShowError.postValue(result.message.orEmpty())
                    }
                    Status.LOADING -> {

                    }
                }
                shouldShowLoading.postValue(false)
            }
        }
    }

    fun getProfile() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = repository.getProfile()
            withContext(Dispatchers.Main) {
                result.let {
                    shouldShowProfile.postValue(it)
                }
            }
        }
    }

    fun increment() {
        viewModelScope.launch {
            repository.increment()
        }
    }

    fun decrement() {
        viewModelScope.launch {
            repository.decrement()
        }
    }


    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: ProfileRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
                return ProfileViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown class name")
        }
    }
}
