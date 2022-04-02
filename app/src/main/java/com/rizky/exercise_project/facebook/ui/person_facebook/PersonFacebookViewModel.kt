package com.rizky.exercise_project.facebook.ui.group_facebook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonFacebookViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is group_facebook Fragment"
    }
    val text: LiveData<String> = _text
}