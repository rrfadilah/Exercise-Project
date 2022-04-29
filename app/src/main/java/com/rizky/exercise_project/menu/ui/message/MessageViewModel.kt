package com.rizky.exercise_project.menu.ui.message

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizky.exercise_project.R
import com.rizky.exercise_project.data.api.MessageResponse
import com.rizky.exercise_project.network.MyDoctorApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessageViewModel: ViewModel() {

    val shouldShowData: MutableLiveData<List<MessageModel>> = MutableLiveData()
    val shouldShowError: MutableLiveData<String> = MutableLiveData()

    private fun loadDataAPI() {
        MyDoctorApiClient.instanceMessage.getMessages()
            .enqueue(object : Callback<List<MessageResponse>> {
                override fun onResponse(
                    call: Call<List<MessageResponse>>,
                    response: Response<List<MessageResponse>>
                ) {
                    val code = response.code()
                    val body = response.body()

                    if (code == 200) {
                        val message = body?.map {
                            MessageModel(
                                id = it.id.orEmpty(),
                                name = it.name.orEmpty(),
                                imageRes = R.drawable.img_user_1,
                                image = it.image.orEmpty(),
                                lastMessage = it.message.orEmpty()
                            )
                        } ?: emptyList()
                        // adapter.updateList(message.sortedBy { it.name })
                        shouldShowData.postValue(message.sortedBy { it.name })
                    } else {
                        // showErrorMessage("Gagal Mengambil data dari API")
                        shouldShowError.postValue("Gagal Mengambil data dari API")

                    }
                }

                override fun onFailure(call: Call<List<MessageResponse>>, t: Throwable) {
                    // showErrorMessage(t.message)
                }
            })
    }
}