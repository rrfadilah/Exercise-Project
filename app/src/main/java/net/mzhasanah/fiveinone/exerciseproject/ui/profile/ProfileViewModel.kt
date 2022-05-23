package net.mzhasanah.fiveinone.exerciseproject.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.mzhasanah.fiveinone.exerciseproject.common.Status
import net.mzhasanah.fiveinone.exerciseproject.model.ProfileModel
import net.mzhasanah.fiveinone.exerciseproject.repository.ProfileRepository
import okhttp3.MultipartBody

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    val shouldShowImage: MutableLiveData<String> = MutableLiveData()
    val shouldShowError: MutableLiveData<String> = MutableLiveData()
    val shouldShowProfile: MutableLiveData<ProfileModel> = MutableLiveData()

    fun onViewLoaded() {
        getProfile()
    }

    fun uploadImage(body: MultipartBody.Part) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repository.uploadImage(body)
            withContext(Dispatchers.Main) {
                when(result.status) {
                    Status.SUCCESS -> {
                        shouldShowImage.postValue(result.data?.data?.image?.url)
                    }
                    Status.ERROR -> {
                        shouldShowError.postValue(result.message.orEmpty())
                    }
                    Status.LOADING -> {

                    }
                }
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