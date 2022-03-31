package com.rizky.exercise_project.menu.ui.lingkedln

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LinkedlnViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ini Fragment LinkedLn"
    }
    val text: LiveData<String> = _text
}