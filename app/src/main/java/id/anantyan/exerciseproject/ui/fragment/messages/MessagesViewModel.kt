package id.anantyan.exerciseproject.ui.fragment.messages

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import id.anantyan.exerciseproject.database.RoomDB
import id.anantyan.exerciseproject.model.Messages
import id.anantyan.exerciseproject.repository.MessagesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MessagesViewModel(application: Application): AndroidViewModel(application) {

    private val repository: MessagesRepository

    init {
        val messagesDao = RoomDB.database(application).messagesDao()
        repository = MessagesRepository(messagesDao)
    }

    fun select() = repository.select()

    fun selectById(id: Int) = repository.selectById(id)

    fun insert(item: Messages) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }

    fun update(item: Messages) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(item)
    }

    fun delete(item: Messages) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(item)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}