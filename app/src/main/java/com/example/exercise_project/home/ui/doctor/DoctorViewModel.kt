package com.example.exercise_project.home.ui.doctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DoctorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is doctor Fragment"
    }
    val text: LiveData<String> = _text
}