package com.rizky.exercise_project.menu.ui.facebook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FacebookViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Ini Fragment Facebook"
    }

    val text: LiveData<String> = _text
}