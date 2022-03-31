package com.rizky.exercise_project.Facebook.ui.notifications_facebook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsFacebookViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications_facebook Fragment"
    }
    val text: LiveData<String> = _text
}