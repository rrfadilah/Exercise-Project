package com.tegarpenemuan.myapplication.home.ui.hospitals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HospitalsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is hospitals Fragment"
    }
    val text: LiveData<String> = _text
}