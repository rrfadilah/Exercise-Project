package id.anantyan.exerciseproject.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.anantyan.exerciseproject.model.Messages
import id.anantyan.exerciseproject.model.Users

class SharedViewModel : ViewModel() {
    private val _item: MutableLiveData<Users?> = MutableLiveData()
    private val _messages: MutableLiveData<Messages?> = MutableLiveData()
    private val _listMessages: MutableLiveData<List<Messages>> = MutableLiveData()

    val item: LiveData<Users?> = _item
    val messages: LiveData<Messages?> = _messages
    val listMessages: LiveData<List<Messages>> = _listMessages

    fun setItem(item: Users?) {
        _item.value = item
    }

    fun setMessages(messages: Messages?) {
        _messages.value = messages
    }

    fun setListMessages(listMessages: List<Messages>) {
        _listMessages.value = listMessages
    }
}