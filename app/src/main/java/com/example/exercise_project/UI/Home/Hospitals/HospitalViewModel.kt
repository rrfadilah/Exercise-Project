package com.example.exercise_project.UI.Home.Hospitals

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exercise_project.data.API.Auth.Home.HospitalResponse
import com.example.exercise_project.database.MyDoctorDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HospitalViewModel: ViewModel() {
    private var db: MyDoctorDatabase? = null
    private var pref: SharedPreferences? = null

    val shouldShowError: MutableLiveData<String> = MutableLiveData()
    val shouldShowHospital: MutableLiveData<List<HospitalResponse>> = MutableLiveData()

    fun onViewLoaded(db: MyDoctorDatabase, preferences: SharedPreferences) {
        this.db = db
        this.pref = preferences

        getHospital()
    }

    private fun getHospital() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = MockApiClient.instanceHome.goodnews()
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    shouldShowHospital.postValue(result.body())
                } else {
                    showErrorMessage(response = result.errorBody())
                }
            }
        }
    }
}