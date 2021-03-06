package com.rizky.exercise_project.ui.signup

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.rizky.exercise_project.data.api.ErrorResponse
import com.rizky.exercise_project.data.api.auth.SignInRequest
import com.rizky.exercise_project.data.api.auth.SignUpRequest
import com.rizky.exercise_project.data.local.UserEntity
import com.rizky.exercise_project.network.MyDoctorApiClient
import com.rizky.exercise_project.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import javax.inject.Inject

/**
 * com.rizky.exercise_project.ui.signup
 *
 * Created by Rizky Fadilah on 23/04/22.
 * https://github.com/rizkyfadilah
 *
 */

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private var name: String = ""
    private var job: String = ""
    private var email: String = ""
    private var password: String = ""

    val shouldShowError: MutableLiveData<String> = MutableLiveData()
    val shouldShowLoading: MutableLiveData<Boolean> = MutableLiveData()
    val shouldOpenUpdateProfile: MutableLiveData<Boolean> = MutableLiveData()

    fun onChangeName(name: String) {
        this.name = name
    }

    fun onChangeJob(job: String) {
        this.job = job
    }

    fun onChangeEmail(email: String) {
        this.email = email
    }

    fun onChangePassword(password: String) {
        this.password = password
    }

    fun onValidate() {
        if (name.isEmpty() && name.length < 3) {
            shouldShowError.postValue("Nama tidak valid")
        } else if (email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            shouldShowError.postValue("Email tidak valid")
        } else if (password.isEmpty() && password.length < 8) {
            shouldShowError.postValue("Password tidak valid")
        } else {
            processToSignUp()
        }
    }

    private fun processToSignUp() {
        CoroutineScope(Dispatchers.IO).launch {
            shouldShowLoading.postValue(true)
            val request = SignUpRequest(
                name = name,
                email = email,
                job = job,
                password = password
            )
            val result = repository.signUp(request = request)
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    processToSignIn()
                } else {
                    showErrorMessage(result.errorBody())
                    shouldShowLoading.postValue(false)
                }
            }
        }
    }

    private fun processToSignIn() {
        CoroutineScope(Dispatchers.IO).launch {
            val request = SignInRequest(
                login = email,
                password = password
            )
            val response = MyDoctorApiClient.instanceAuth.signIn(request)
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
                    shouldShowLoading.postValue(false)
                } else {
                    showErrorMessage(response.errorBody())
                    shouldShowLoading.postValue(false)
                }
            }
        }
    }

    private fun showErrorMessage(response: ResponseBody?) {
        val error =
            Gson().fromJson(response?.string(), ErrorResponse::class.java)
        shouldShowError.postValue(error.message.orEmpty() + " #${error.code}")
    }

    private fun insertToken(token: String) {
        if (token.isNotEmpty()) {
            viewModelScope.launch {
                repository.updateToken(token)
            }
        }
    }

    private fun insertProfile(userEntity: UserEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repository.insertUser(userEntity)
            withContext(Dispatchers.Main) {
                if (result != 0L) {
                    shouldOpenUpdateProfile.postValue(true)
                } else {
                    shouldShowError.postValue("Maaf, Gagal insert ke dalam database")
                }
            }
        }
    }
}