package id.anantyan.exerciseproject.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.anantyan.exerciseproject.model.DataDummy

class SharedViewModel : ViewModel() {
    private val _item: MutableLiveData<DataDummy> = MutableLiveData()
    val item: LiveData<DataDummy> = _item
    fun setItem(item: DataDummy) {
        _item.value = item
    }
}