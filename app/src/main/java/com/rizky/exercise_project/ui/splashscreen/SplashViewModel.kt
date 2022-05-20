package com.rizky.exercise_project.ui.splashscreen

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.rizky.exercise_project.Constant
import com.rizky.exercise_project.common.Status
import com.rizky.exercise_project.repository.AuthRepository
import com.rizky.exercise_project.repository.ProfileRepository
import com.rizky.exercise_project.ui.profile.ProfileViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * com.rizky.exercise_project.ui.splashscreen
 *
 * Created by Rizky Fadilah on 16/04/22.
 * https://github.com/rizkyfadilah
 *
 */

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    val shouldOpenOnBoarding: MutableLiveData<Boolean> = MutableLiveData()
    val shouldOpenHomePage: MutableLiveData<Boolean> = MutableLiveData()

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
}
