package net.mzhasanah.fiveinone.exerciseproject.ui.home.doctor

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.mzhasanah.fiveinone.exerciseproject.Constant
import net.mzhasanah.fiveinone.exerciseproject.data.api.ErrorResponse
import net.mzhasanah.fiveinone.exerciseproject.data.api.home.ConsultationResponse
import net.mzhasanah.fiveinone.exerciseproject.data.api.home.GoodNewsResponse
import net.mzhasanah.fiveinone.exerciseproject.data.api.home.TopRatedResponse
import net.mzhasanah.fiveinone.exerciseproject.database.MyDoctorDatabase
import net.mzhasanah.fiveinone.exerciseproject.model.ProfileModel
import net.mzhasanah.fiveinone.exerciseproject.network.MockApiClient
import net.mzhasanah.fiveinone.exerciseproject.network.MyDoctorApiClient
import net.mzhasanah.fiveinone.exerciseproject.repository.ProfileRepository
import okhttp3.ResponseBody

class DoctorViewModel(private val repository: ProfileRepository) : ViewModel() {
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

//    private fun getProfile() {
//        CoroutineScope(Dispatchers.Main).launch {
//            val result = db?.userDAO()?.getUser()
//            withContext(Dispatchers.Main) {
//                result?.let {
//                    val profile = ProfileModel(
//                        id = it.id,
//                        name = it.name,
//                        image = it.image,
//                        job = it.job
//                    )
//                    shouldShowProfile.postValue(profile)
//                } ?: run {
//                    showErrorMessage(message = "Data Kosong")
//                }
//            }
//        }
//    }

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

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: ProfileRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DoctorViewModel::class.java)) {
                return DoctorViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown class name")
        }
    }
}