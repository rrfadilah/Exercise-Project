package id.anantyan.exerciseproject.repository

import androidx.lifecycle.MutableLiveData
import id.anantyan.exerciseproject.data.local.MessagesDao
import id.anantyan.exerciseproject.model.Messages
import id.anantyan.exerciseproject.model.MessagesList
import id.anantyan.exerciseproject.network.RetrofitNetwork
import id.anantyan.utils.LiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessagesRepository(private val usersDao: MessagesDao) {

    val _selectApiSuccess: MutableLiveData<List<Messages>> = MutableLiveData()
    val _failure: LiveEvent<String> = LiveEvent()
    val _seectById: LiveEvent<Messages> = LiveEvent()
    val _insert: LiveEvent<Messages> = LiveEvent()
    val _update: LiveEvent<Messages> = LiveEvent()
    val _delete: LiveEvent<Messages> = LiveEvent()
    var _deleteAll: LiveEvent<Unit> = LiveEvent()

    fun selectLocal() = usersDao.select()
    fun selectApi() = RetrofitNetwork.messagesApi.getMessage()
        .enqueue(object : Callback<MessagesList> {
            override fun onResponse(call: Call<MessagesList>, response: Response<MessagesList>) {
                if (response.isSuccessful) {
                    _selectApiSuccess.postValue(response.body()?.message!!)
                } else {
                    _failure.postValue("Gagal membuat data API!")
                }
            }

            override fun onFailure(call: Call<MessagesList>, t: Throwable) {
                _failure.postValue("${t.message}")
            }
        })

    suspend fun selectById(id: Int) {
        _seectById.postValue(usersDao.selectById(id))
    }

    suspend fun insert(item: Messages) {
        usersDao.insert(item)
        _insert.postValue(item)
    }

    suspend fun update(item: Messages) {
        usersDao.update(item)
        _update.postValue(item)
    }

    suspend fun delete(item: Messages) {
        usersDao.delete(item)
        _delete.postValue(item)
    }

    suspend fun deleteAll() {
        _deleteAll.postValue(usersDao.deleteAll())
    }
}