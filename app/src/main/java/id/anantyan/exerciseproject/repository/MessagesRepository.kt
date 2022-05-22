package id.anantyan.exerciseproject.repository

import androidx.lifecycle.MutableLiveData
import id.anantyan.exerciseproject.data.api.MessagesApi
import id.anantyan.exerciseproject.data.local.MessagesDao
import id.anantyan.exerciseproject.model.Messages
import id.anantyan.utils.LiveEvent
import id.anantyan.utils.Resource
import javax.inject.Inject

class MessagesRepository @Inject constructor(
    private val messagesDao: MessagesDao,
    private val messagesApi: MessagesApi
) {

    val _selectResponse: MutableLiveData<Resource<List<Messages>>> = MutableLiveData()
    val _insertResponse: LiveEvent<Resource<Messages>> = LiveEvent()
    val _updateResponse: LiveEvent<Resource<Messages>> = LiveEvent()
    val _deleteResponse: LiveEvent<Resource<Messages>> = LiveEvent()

    val _seectById: LiveEvent<Messages> = LiveEvent()

    val _insertLocal: LiveEvent<Messages> = LiveEvent()
    val _updateLocal: LiveEvent<Messages> = LiveEvent()
    val _deleteLocal: LiveEvent<Messages> = LiveEvent()

    var _deleteAll: LiveEvent<Unit> = LiveEvent()

    fun selectLocal() = messagesDao.select()
    suspend fun selectApi() {
        _selectResponse.postValue(Resource.Loading())
        try {
            val response = messagesApi.getMessage()
            if (response.isSuccessful) {
                response.body()?.let {
                    _selectResponse.postValue(Resource.Success(it))
                }
            } else {
                throw Exception("Gagal memuat data API!")
            }
        } catch (ex: Exception) {
            ex.message?.let {
                _selectResponse.postValue(Resource.Error(it))
            }
        }
    }

    suspend fun selectById(id: String) {
        _seectById.postValue(messagesDao.selectById(id))
    }

    suspend fun insertLocal(item: Messages) {
        messagesDao.insert(item)
        _insertLocal.postValue(item)
    }
    suspend fun insertApi(item: Messages) {
        _insertResponse.postValue(Resource.Loading())
        try {
            val response = messagesApi.postMessage(item)
            if (response.isSuccessful) {
                response.body()?.let {
                    _insertResponse.postValue(Resource.Success(item, "Data berhasil ditambahkan!"))
                }
            } else {
                throw Exception("Gagal memuat data API!")
            }
        } catch (ex: Exception) {
            ex.message?.let {
                _selectResponse.postValue(Resource.Error(it))
            }
        }
    }

    suspend fun updateLocal(item: Messages) {
        messagesDao.update(item)
        _updateLocal.postValue(item)
    }
    suspend fun updateApi(item: Messages) {
        _updateResponse.postValue(Resource.Loading())
        try {
            val response = messagesApi.updateMessages(item.id, item)
            if (response.isSuccessful) {
                response.body()?.let {
                    _updateResponse.postValue(Resource.Success(item, "Data berhasil diubah!"))
                }
            } else {
                throw Exception("Gagal memuat data API!")
            }
        } catch (ex: Exception) {
            ex.message?.let {
                _updateResponse.postValue(Resource.Error(it))
            }
        }
    }

    suspend fun deleteLocal(item: Messages) {
        messagesDao.delete(item)
        _deleteLocal.postValue(item)
    }
    suspend fun deleteApi(item: Messages) {
        _deleteResponse.postValue(Resource.Loading())
        try {
            val response = messagesApi.deleteMessages(item.id)
            if (response.isSuccessful) {
                response.body()?.let {
                    _deleteResponse.postValue(Resource.Success(item, "Data berhasil dihapus!"))
                }
            } else {
                throw Exception("Gagal memuat data API!")
            }
        } catch (ex: Exception) {
            ex.message?.let {
                _deleteResponse.postValue(Resource.Error(it))
            }
        }
    }

    suspend fun deleteAll() {
        _deleteAll.postValue(messagesDao.deleteAll())
    }
}