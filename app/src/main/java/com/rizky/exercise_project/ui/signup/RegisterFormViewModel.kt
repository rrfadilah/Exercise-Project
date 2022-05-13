package com.rizky.exercise_project.ui.signup

import android.content.SharedPreferences
import android.net.DnsResolver
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizky.exercise_project.data.api.auth.SignInRequest
import com.rizky.exercise_project.data.api.auth.register.RegisterRequest
import com.rizky.exercise_project.data.api.auth.register.RegisterResponse
import com.rizky.exercise_project.data.local.UserEntity
import com.rizky.exercise_project.database.MyDoctorDatabase
import com.rizky.exercise_project.network.MyDoctorApiClient
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class RegisterFormViewModel(): ViewModel() {

    private var db: MyDoctorDatabase? = null
    private var pref: SharedPreferences? = null

    private var fullname: String = ""
    private var pekerjaan: String = ""
    private var email: String = ""
    private var password: String = ""

    val shouldShowError: MutableLiveData<String> = MutableLiveData()
    val shouldOpenSignIn: MutableLiveData<Boolean> = MutableLiveData()

    fun onViewLoaded(db: MyDoctorDatabase, preferences: SharedPreferences) {
        this.db = db
        this.pref = preferences
    }

    fun onChangeFullName(fullname: String) {
        this.fullname = fullname
    }

    fun onChangeJob(job: String) {
        this.pekerjaan = job
    }

    fun onChangeEmail(email: String) {
        this.email = email
    }

    fun onChangePassword(password: String) {
        this.password = password
    }

    fun onClickRegister() {

        val register = RegisterRequest(
            name = fullname,
            job = pekerjaan,
            email = email,
            password = password
        )

        if(fullname.isEmpty()) {
            shouldShowError.postValue("Nama harus diisi")
        } else if (pekerjaan.isEmpty()) {
            shouldShowError.postValue("Pekerjaan harus diisi")
        } else if (email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            shouldShowError.postValue("Email tidak valid")
        } else if (password.isEmpty() && password.length < 8) {
            shouldShowError.postValue("Password tidak valid")
        } else {
            registerFormAPI(register)
        }
    }

    private fun registerFormAPI(register: RegisterRequest) {
        MyDoctorApiClient.instanceAuth.register(request = register)
            .enqueue(object : retrofit2.Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    val code = response.code()
                    val body = response.body()

                    if (code == 200) {
                        shouldShowError.postValue("Register Berhasil!")
                        shouldOpenSignIn.postValue(true)
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    shouldShowError.postValue(t.cause.toString())
                }

            })
    }


}