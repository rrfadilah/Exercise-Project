package id.anantyan.exerciseproject.ui.fragment.messages

import android.app.Application
import androidx.lifecycle.*
import id.anantyan.exerciseproject.database.RoomDB
import id.anantyan.exerciseproject.model.Messages
import id.anantyan.exerciseproject.model.MessagesResponse
import id.anantyan.exerciseproject.repository.MessagesRepository
import id.anantyan.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MessagesViewModel(application: Application): AndroidViewModel(application) {

    private val repository: MessagesRepository

    val selectResponse: LiveData<Resource<List<Messages>>>
    val insertResponse: LiveData<Resource<Messages>>
    val updateResponse: LiveData<Resource<Messages>>
    val deleteResponse: LiveData<Resource<Messages>>

    val insertLocal: LiveData<Messages>
    val updateLocal: LiveData<Messages>
    val deleteLocal: LiveData<Messages>

    init {
        val messagesDao = RoomDB.database(application).messagesDao()
        repository = MessagesRepository(messagesDao)

        selectResponse = repository._selectResponse
        insertResponse = repository._insertResponse
        updateResponse = repository._updateResponse
        deleteResponse = repository._deleteResponse

        insertLocal = repository._insertLocal
        updateLocal = repository._updateLocal
        deleteLocal = repository._deleteLocal
    }

    fun selectLocal() = repository.selectLocal()
    fun selectApi() = CoroutineScope(Dispatchers.IO).launch {
        repository.selectApi()
    }

    fun insertLocal(item: Messages) = CoroutineScope(Dispatchers.IO).launch {
        repository.insertLocal(item)
    }
    fun insertApi(item: Messages) = CoroutineScope(Dispatchers.IO).launch {
        repository.insertApi(item)
    }

    fun updateLocal(item: Messages) = CoroutineScope(Dispatchers.IO).launch {
        repository.updateLocal(item)
    }
    fun updateApi(item: Messages) = CoroutineScope(Dispatchers.IO).launch {
        repository.updateApi(item)
    }

    fun deleteLocal(item: Messages) = CoroutineScope(Dispatchers.IO).launch {
        repository.deleteLocal(item)
    }
    fun deleteApi(item: Messages) = CoroutineScope(Dispatchers.IO).launch {
        repository.deleteApi(item)
    }
}