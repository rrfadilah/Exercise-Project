package com.example.exercise_project.ui.home.ui.hospital

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercise_project.data.api.ErrorResponse
import com.example.exercise_project.data.api.home.NearbyHospitalResponse
import com.example.exercise_project.network.DoctorApi
import com.example.exercise_project.ui.home.database.MyDoctorDatabase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

class HospitalViewModel : ViewModel() {

    private var db: MyDoctorDatabase? = null
    private var pref: SharedPreferences? = null

    val shouldShowError: MutableLiveData<String> = MutableLiveData()
    val shouldShowHospital: MutableLiveData<List<NearbyHospitalResponse>> = MutableLiveData()

    fun onViewLoaded(db: MyDoctorDatabase, preferences: SharedPreferences) {
        this.db = db
        this.pref = preferences

        getHospital()
    }

    private fun getHospital() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = DoctorApi.instanceHome.hospital()
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    shouldShowHospital.postValue(result.body())
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