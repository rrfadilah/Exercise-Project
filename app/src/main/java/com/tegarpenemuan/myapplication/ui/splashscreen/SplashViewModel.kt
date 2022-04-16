package com.tegarpenemuan.myapplication.ui.splashscreen

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tegarpenemuan.myapplication.Constant

class SplashViewModel: ViewModel() {
    val shouldOpenOnBoarding: MutableLiveData<Boolean> = MutableLiveData()
    val shouldOpenHomePage: MutableLiveData<Boolean> = MutableLiveData()

    fun onViewLoaded(sharedPreferences: SharedPreferences) {
        if (sharedPreferences.getString(Constant.Preferences.KEY.TOKEN, "").isNullOrEmpty()) {
            shouldOpenOnBoarding.postValue(true)
        } else {
            shouldOpenHomePage.postValue(true)
        }
    }
}