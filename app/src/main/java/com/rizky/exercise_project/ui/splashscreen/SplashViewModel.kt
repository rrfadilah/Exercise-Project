package com.rizky.exercise_project.ui.splashscreen

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.common.Status
import com.rizky.exercise_project.repository.AuthRepository
import com.rizky.exercise_project.repository.ProfileRepository
import com.rizky.exercise_project.ui.profile.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * com.rizky.exercise_project.ui.splashscreen
 *
 * Created by Rizky Fadilah on 16/04/22.
 * https://github.com/rizkyfadilah
 *
 */

class SplashViewModel(
    private val repository: AuthRepository
) : ViewModel() {
    val shouldOpenOnBoarding: MutableLiveData<Boolean> = MutableLiveData()
    val shouldOpenHomePage: MutableLiveData<Boolean> = MutableLiveData()
//    val shouldGetToken: LiveData<String> = repository.getToken().asLiveData()

    fun onViewLoaded(sharedPreferences: SharedPreferences) {
        if (sharedPreferences.getString(Constant.Preferences.KEY.TOKEN, "").isNullOrEmpty()) {
            shouldOpenOnBoarding.postValue(true)
        } else {
            shouldOpenHomePage.postValue(true)
        }
    }

    fun onViewLoaded() {
        viewModelScope.launch {
            val result = repository.getToken()
            withContext(Dispatchers.Main) {
                if (result.isNullOrEmpty()) {
                    shouldOpenOnBoarding.postValue(true)
                } else {
                    shouldOpenHomePage.postValue(true)
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: AuthRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
                return SplashViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown class name")
        }
    }
}
