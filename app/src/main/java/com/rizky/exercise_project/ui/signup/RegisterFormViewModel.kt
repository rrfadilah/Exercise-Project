package com.rizky.exercise_project.ui.signup

import android.content.SharedPreferences
import android.net.DnsResolver
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizky.exercise_project.data.api.auth.register.RegisterRequest
import com.rizky.exercise_project.data.api.auth.register.RegisterResponse
import com.rizky.exercise_project.database.MyDoctorDatabase
import com.rizky.exercise_project.network.MyDoctorApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    fun onChangeJib(job: String) {
        this.pekerjaan = job
    }

    fun onChangeEmail(email: String) {
        this.email = email
    }

    fun onChangePassword(password: String) {
        this.password = password
    }

    fun onClickRegister() {
        if(fullname.isEmpty()) {
            shouldShowError.postValue("Nama harus diisi")
        } else if (pekerjaan.isEmpty()) {
            shouldShowError.postValue("Pekerjaan harus diisi")
        } else if (email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            shouldShowError.postValue("Email tidak valid")
        } else if (password.isEmpty() && password.length < 8) {
            shouldShowError.postValue("Password tidak valid")
        } else {
            shouldShowError.postValue("OK")
        }
    }


    private fun registerEmailAlreadyTaken() {
        CoroutineScope(Dispatchers.IO).launch {
            val request = RegisterRequest(
                email = email
            )

            val registerData = RegisterRequest(
            )
        }
    }

}