package net.mzhasanah.fiveinone.exerciseproject.ui.profile

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.mzhasanah.fiveinone.exerciseproject.common.Status
import net.mzhasanah.fiveinone.exerciseproject.model.ProfileModel
import net.mzhasanah.fiveinone.exerciseproject.repository.ProfileRepository
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel() {

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
                when(result.status) {
                    Status.SUCCESS -> {
                        shouldShowImage.postValue(result.data?.data?.image?.url)
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
}