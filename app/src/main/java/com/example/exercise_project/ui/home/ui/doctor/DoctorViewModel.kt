package com.example.exercise_project.ui.home.ui.doctor

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercise_project.Constant
import com.example.exercise_project.data.api.ErrorResponse
import com.example.exercise_project.data.api.home.ConsultationResponse
import com.example.exercise_project.data.api.home.GoodNewsResponse
import com.example.exercise_project.data.api.home.TopRatedResponse
import com.example.exercise_project.model.ProfileModel
import com.example.exercise_project.network.DoctorApi
import com.example.exercise_project.network.MyDoctorApiClient
import com.example.exercise_project.ui.home.database.MyDoctorDatabase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

class DoctorViewModel : ViewModel() {

    private var db: MyDoctorDatabase? = null
    private var pref: SharedPreferences? = null

    val shouldShowError: MutableLiveData<String> = MutableLiveData()
    val shouldShowProfile: MutableLiveData<ProfileModel> = MutableLiveData()
    val shouldShowLoading: MutableLiveData<Boolean> = MutableLiveData()

    val shouldShowConsultation: MutableLiveData<List<ConsultationResponse>> = MutableLiveData()
    val shouldShowTopRated: MutableLiveData<List<TopRatedResponse>> = MutableLiveData()
    val shouldShowGoodNews: MutableLiveData<List<GoodNewsResponse>> = MutableLiveData()

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
            val headers = mapOf(
                "user-token" to pref?.getString(Constant.Preferences.KEY.TOKEN, "").orEmpty()
            )
            val result = MyDoctorApiClient.instanceAuth.logout(headers = headers)
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    // clear data profile dari room
                    // clear token dari preferences
                    shouldShowLoading.postValue(false)
                } else {
                    showErrorMessage(response = result.errorBody())
                    shouldShowLoading.postValue(false)

                }
            }
        }
    }

    private fun getProfile() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = db?.userDAO()?.getUser()
            withContext(Dispatchers.Main) {
                result?.let {
                    val profile = ProfileModel(
                        id = it.id,
                        name = it.name,
                        image = it.image,
                        job = it.job
                    )
                    shouldShowProfile.postValue(profile)
                } ?: run {
                    showErrorMessage(message = "Data Kosong")
                }
            }
        }
    }

    private fun getConsultations() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = DoctorApi.instanceHome.consultations()
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
            val result = DoctorApi.instanceHome.toprates()
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
            val result = DoctorApi.instanceHome.goodnews()
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