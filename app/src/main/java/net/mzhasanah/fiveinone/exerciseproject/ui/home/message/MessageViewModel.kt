package net.mzhasanah.fiveinone.exerciseproject.ui.home.message

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.mzhasanah.fiveinone.exerciseproject.R
import net.mzhasanah.fiveinone.exerciseproject.data.api.MessagesRequest
import net.mzhasanah.fiveinone.exerciseproject.network.MyDoctorApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MessageViewModel : ViewModel() {
    val shouldShowData: MutableLiveData<List<MessageModel>> = MutableLiveData()
    val shouldShowError: MutableLiveData<String> = MutableLiveData()

    // function untuk load data dari api
    fun loadDataAPI() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = MyDoctorApiClient.instanceMessage.getMessages()
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    val message = result.body()?.map {
                        MessageModel(
                            id = it.id.orEmpty(),
                            name = it.name.orEmpty(),
                            imageRes = R.drawable.img_user_1,
                            image = it.image.orEmpty(),
                            lastMessage = it.message.orEmpty()
                        )
                    } ?: emptyList()
                    shouldShowData.postValue(message.sortedBy { it.name })
                } else {
                    shouldShowError.postValue("Gagal Mengambil data dari API")
                }
            }
        }
    }

    // function untuk menginsert data pada api
    fun postDataAPI(message: MessagesRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = MyDoctorApiClient.instanceMessage.postMessages(request = message)
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    loadDataAPI()
                } else {
                    shouldShowError.postValue("Gagal membuat data API")
                }
            }
        }
    }

    // function untuk menghapus data pada api
    fun deleteDataAPI(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = MyDoctorApiClient.instanceMessage.deleteMessages(id = id)
            withContext(Dispatchers.Main) {
                if (result.isSuccessful) {
                    loadDataAPI()
                } else {
                    shouldShowError.postValue("Gagal menghapus data API")
                }
            }
        }
    }

    // function untuk mengupdate data pada api
    fun updateDataAPI(id: String, message: MessagesRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            val result =
                MyDoctorApiClient.instanceMessage.updateMessages(id = id, request = message)
            withContext(Dispatchers.IO) {
                if (result.isSuccessful) {
                    loadDataAPI()
                } else {
                    shouldShowError.postValue("Gagal mengupdate data API")
                }
            }
        }
    }
}