package com.rizky.exercise_project.Facebook.ui.group_facebook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveFacebookViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is group_facebook Fragment"
    }
    val text: LiveData<String> = _text
}