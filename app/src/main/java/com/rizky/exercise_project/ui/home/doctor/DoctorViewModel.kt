package com.rizky.exercise_project.ui.home.doctor

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.rizky.exercise_project.data.api.ErrorResponse
import com.rizky.exercise_project.data.api.home.ConsultationResponse
import com.rizky.exercise_project.data.api.home.GoodNewsResponse
import com.rizky.exercise_project.data.api.home.TopRatedResponse
import com.rizky.exercise_project.database.MyDoctorDatabase
import com.rizky.exercise_project.model.ProfileModel
import com.rizky.exercise_project.network.MockApiClient
import com.rizky.exercise_project.repository.AuthRepository
import com.rizky.exercise_project.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject

/**
 * com.rizky.exercise_project.ui.home.doctor
 *
 * Created by Rizky Fadilah on 23/04/22.
 * https://github.com/rizkyfadilah
 *
 */

@HiltViewModel
class DoctorViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    private var db: MyDoctorDatabase? = null
    private var pref: SharedPreferences? = null

    val shouldShowError: MutableLiveData<String> = MutableLiveData()
    val shouldShowProfile: MutableLiveData<ProfileModel> = MutableLiveData()
    val shouldShowLoading: MutableLiveData<Boolean> = MutableLiveData()

    val shouldShowConsultation: MutableLiveData<List<ConsultationResponse>> = MutableLiveData()
    val shouldShowTopRated: MutableLiveData<List<TopRatedResponse>> = MutableLiveData()
    val shouldShowGoodNews: MutableLiveData<List<GoodNewsResponse>> = MutableLiveData()

    val shouldShowGetStarted: MutableLiveData<Boolean> = MutableLiveData()

    fun onViewLoaded(db: MyDoctorDatabase, preferences: SharedPreferences) {
        this.db = db
        this.pref = preferences

        getProfile()
        getConsultations()
        getTopRated()
        getGoodNews()
    }

    fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            shouldShowLoading.postValue(true)
            val result = authRepository.logout()
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    // clear data profile dari room
                    // clear token dari preferences
                    shouldShowLoading.postValue(false)
                    clearToken()
                } else {
                    showErrorMessage(response = result.errorBody())
                    shouldShowLoading.postValue(false)

                }
            }
        }
    }

    private fun clearToken() {
        viewModelScope.launch {
            authRepository.clearToken()
            repository.deleteProfile()
            withContext(Dispatchers.Main) {
                shouldShowGetStarted.postValue(true)
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

    private fun getConsultations() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = MockApiClient.instanceHome.consultations()
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    shouldShowConsultation.postValue(result.body())
                } else {
                    showErrorMessage(response = result.errorBody())
                }
            }
        }
    }

    private fun getTopRated() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = MockApiClient.instanceHome.toprates()
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    shouldShowTopRated.postValue(result.body())
                } else {
                    showErrorMessage(response = result.errorBody())
                }
            }
        }
    }

    private fun getGoodNews() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = MockApiClient.instanceHome.goodnews()
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    shouldShowGoodNews.postValue(result.body())
                } else {
                    showErrorMessage(response = result.errorBody())
                }
            }
        }
    }

    private fun showErrorMessage(response: ResponseBody? = null, message: String? = null) {
        val error =
            Gson().fromJson(response?.string() ?: message ?: "", ErrorResponse::class.java)
        shouldShowError.postValue(error.message.orEmpty() + " #${error.code}")
    }
}