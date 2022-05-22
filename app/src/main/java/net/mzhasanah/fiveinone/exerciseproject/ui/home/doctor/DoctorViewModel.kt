package net.mzhasanah.fiveinone.exerciseproject.ui.home.doctor

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.mzhasanah.fiveinone.exerciseproject.Constant
import net.mzhasanah.fiveinone.exerciseproject.data.api.ErrorResponse
import net.mzhasanah.fiveinone.exerciseproject.database.MyDoctorDatabase
import net.mzhasanah.fiveinone.exerciseproject.network.MyDoctorApiClient
import okhttp3.ResponseBody

class DoctorViewModel : ViewModel() {
    private var db: MyDoctorDatabase? = null
    private var pref: SharedPreferences? = null

    val shouldShowError: MutableLiveData<String> = MutableLiveData()
    val shouldShowImageProfile: MutableLiveData<String> = MutableLiveData()
    val shouldShowLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun onViewLoaded(db: MyDoctorDatabase, preferences: SharedPreferences) {
        this.db = db
        this.pref = preferences

        getProfile()
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
                    shouldShowImageProfile.postValue(it.image)
                } ?: run {
                    showErrorMessage(message = "Data Kosong")
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