package net.mzhasanah.fiveinone.exerciseproject.ui.signin

import android.content.SharedPreferences
import android.util.Patterns
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import net.mzhasanah.fiveinone.exerciseproject.Constant
import net.mzhasanah.fiveinone.exerciseproject.data.api.ErrorResponse
import net.mzhasanah.fiveinone.exerciseproject.data.api.auth.SignInRequest
import net.mzhasanah.fiveinone.exerciseproject.data.local.UserEntity
import net.mzhasanah.fiveinone.exerciseproject.database.MyDoctorDatabase
import net.mzhasanah.fiveinone.exerciseproject.network.MyDoctorApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.mzhasanah.fiveinone.exerciseproject.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private var db: MyDoctorDatabase? = null
    private var pref: SharedPreferences? = null

    private var email: String = ""
    private var password: String = ""

    val shouldShowError: MutableLiveData<String> = MutableLiveData()
    val shouldOpenHomePage: MutableLiveData<Boolean> = MutableLiveData()

    fun onViewLoaded(db: MyDoctorDatabase, preferences: SharedPreferences) {
        this.db = db
        this.pref = preferences
    }

    fun onChangeEmail(email: String) {
        this.email = email
    }

    fun onChangePassword(password: String) {
        this.password = password
    }

    fun onClickSignIn() {
        if (email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            shouldShowError.postValue("Email tidak valid")
        } else if (password.isEmpty() && password.length < 8) {
            shouldShowError.postValue("Password tidak valid")
        } else {
            signInFromAPI()
        }
    }

    private fun signInFromAPI() {
        CoroutineScope(Dispatchers.IO).launch {
            val request = SignInRequest(
                login = email,
                password = password
            )
            val response = authRepository.signIn(request)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val signInResponse = response.body()
                    signInResponse?.let {
                        // mempersiapkan untuk simpan token
                        insertToken(it.userToken.orEmpty())

                        // mempersiapkan untuk insert ke database
                        val userEntity = UserEntity(
                            id = it.objectId.orEmpty(),
                            name = it.name.orEmpty(),
                            email = it.email.orEmpty(),
                            job = it.job.orEmpty(),
                            image = it.image.orEmpty()
                        )
                        insertProfile(userEntity)
                    }
                } else {
                    val error =
                        Gson().fromJson(response.errorBody()?.string(), ErrorResponse::class.java)
                    shouldShowError.postValue(error.message.orEmpty() + " #${error.code}")
                }
            }
        }
    }

    private fun insertToken(token: String) {
        if (token.isNotEmpty()) {
            viewModelScope.launch {
                authRepository.updateToken(token)
            }
        }
    }

    private fun insertProfile(userEntity: UserEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = db?.userDAO()?.insertUser(userEntity)
            withContext(Dispatchers.Main) {
                if (result != 0L) {
                    shouldOpenHomePage.postValue(true)
                } else {
                    shouldShowError.postValue("Maaf, Gagal insert ke dalam database")
                }
            }
        }
    }
}