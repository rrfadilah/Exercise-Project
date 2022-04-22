package com.example.exercise_project.home.ui.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.exercise_project.R
import com.example.exercise_project.data.api.MessagesRequest
import com.example.exercise_project.data.api.MessagesResponse
import com.example.exercise_project.databinding.FragmentMessagesBinding
import com.example.exercise_project.data.local.MessageEntity
import com.example.exercise_project.home.database.MyDoctorDatabase
import com.example.exercise_project.network.MyDoctorApiClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessagesFragment : Fragment() {
    private var _binding: FragmentMessagesBinding? = null
    private val binding get() = _binding!!

    private var db: MyDoctorDatabase? = null
    private lateinit var adapter: MessageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = MyDoctorDatabase.getInstance(requireContext().applicationContext)

        adapter = MessageAdapter(
            listener = object : MessageAdapter.EventListener {
                override fun onClick(item: MessageModel) {
                    Toast.makeText(requireContext(), item.lastMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onDelete(item: MessageModel) {
//                    val message = MessageEntity(
//                        id = item.id,
//                        name = item.name,
//                        image = item.image,
//                        message = item.lastMessage
//                    )
//                    deleteDataDatabase(message)
                    deleteDataAPI(item.id)
                }

                override fun onUpdate(item: MessageModel) {
//                    val message = MessageEntity(
//                        id = item.id,
//                        name = item.name + " Updated",
//                        image = item.image,
//                        message = item.lastMessage + " Updated"
//                    )
//                    updateDataDatabase(message)

                    val message = MessagesRequest(
                        name = "#" + item.name,
                        image = item.image,
                        message = "# " + item.lastMessage
                    )
                    updateDataAPI(id = item.id, message = message)
                }
            },
            list = emptyList()
        )

        binding.rvMessage.adapter = adapter

//        loadDataDatabase()
        loadDataAPI()

        binding.fabPlus.setOnClickListener {
//            val message = MessageEntity(
//                id = System.currentTimeMillis().toString(),
//                image = System.currentTimeMillis().toString(),
//                name = System.currentTimeMillis().toString() + " This is Name",
//                message = System.currentTimeMillis().toString() + " This is Last Messages"
//            )
//
//            insertDataDatabase(message)

            val message = MessagesRequest(
                image = "https://www.dmarge.com/wp-content/uploads/2021/01/dwayne-the-rock-.jpg",
                name = System.currentTimeMillis().toString() + " This is Name",
                message = System.currentTimeMillis().toString() + " This is Last Messages"
            )

            postDataAPI(message)
        }
    }

    // function untuk load data dummy
    fun loadDataDummy(): List<MessageModel> {
        return listOf(
            MessageModel(
                id = "1",
                name = "Rizky Fadilah",
                imageRes = R.drawable.img_user1,
                lastMessage = "Ini Contoh Message nya."
            ),
            MessageModel(
                id = "2",
                name = "Rizky Fadilah 2",
                imageRes = R.drawable.img_user2,
                lastMessage = "Ini Contoh Message nya Yang kedua."
            ),
        )
    }

    // function untuk insert data pada database
    private fun insertDataDatabase(message: MessageEntity) {
        GlobalScope.async {
            val result = db?.messageDAO()?.insertMessage(message)
            requireActivity().runOnUiThread {
                if (result != 0L) {
                    Toast.makeText(requireContext(), "Success insert", Toast.LENGTH_SHORT)
                        .show()
                    loadDataDatabase()
                } else {
                    Toast.makeText(requireContext(), "Failure insert", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    // function untuk update data pada database
    private fun updateDataDatabase(message: MessageEntity) {
        GlobalScope.async {
            val result = db?.messageDAO()?.updateMessage(message)
            requireActivity().runOnUiThread {
                if (result != 0) {
                    Toast.makeText(requireContext(), "Success update", Toast.LENGTH_SHORT)
                        .show()
                    loadDataDatabase()
                } else {
                    Toast.makeText(requireContext(), "Failure update", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    // function untuk load data dari database
    private fun loadDataDatabase() {
        GlobalScope.launch {
            val results = db?.messageDAO()?.getMessage()
            requireActivity().runOnUiThread {
                results?.let { it ->
                    val messages = it.map {
                        MessageModel(
                            id = it.id,
                            name = it.name,
                            imageRes = R.drawable.img_user1,
                            image = it.image,
                            lastMessage = it.message
                        )
                    }
                    adapter.updateList(messages)
                }
            }
        }
    }

    // function untuk mendelete data pada databse
    private fun deleteDataDatabase(message: MessageEntity) {
        GlobalScope.async {
            val results = db?.messageDAO()?.deleteMessage(message)
            requireActivity().runOnUiThread {
                if (results != 0) {
                    Toast.makeText(requireContext(), "Berhasil delete", Toast.LENGTH_SHORT).show()
                    loadDataDatabase()
                } else {
                    Toast.makeText(requireContext(), "Gagal delete", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    // function untuk load data dari api
    private fun loadDataAPI() {
        MyDoctorApiClient.instanceMessage.getMessages()
            .enqueue(object : Callback<List<MessagesResponse>> {
                override fun onResponse(
                    call: Call<List<MessagesResponse>>,
                    response: Response<List<MessagesResponse>>
                ) {
                    val code = response.code()
                    val body = response.body()

                    if (code == 200) {
                        val message = body?.map {
                            MessageModel(
                                id = it.id.orEmpty(),
                                name = it.name.orEmpty(),
                                imageRes = R.drawable.img_user1,
                                image = it.image.orEmpty(),
                                lastMessage = it.message.orEmpty()
                            )
                        } ?: emptyList()
                        adapter.updateList(message.sortedBy { it.name })
                    } else {
                        showErrorMessage("Gagal Mengambil data dari API")
                    }
                }

                override fun onFailure(call: Call<List<MessagesResponse>>, t: Throwable) {
                    showErrorMessage(t.message)
                }
            })
    }

    // function untuk menginsert data pada api
    private fun postDataAPI(message: MessagesRequest) {
        MyDoctorApiClient.instanceMessage.postMessages(request = message)
            .enqueue(object : Callback<MessagesResponse> {
                override fun onResponse(
                    call: Call<MessagesResponse>,
                    response: Response<MessagesResponse>
                ) {
                    val code = response.code()
                    val body = response.body()

                    if (code == 200) {
                        loadDataAPI()
                    } else {
                        showErrorMessage("Gagal Mengambil data dari API")
                    }
                }

                override fun onFailure(call: Call<MessagesResponse>, t: Throwable) {
                    showErrorMessage(t.message)
                }
            })
    }

    // function untuk menghapus data pada api
    private fun deleteDataAPI(id: String) {
        MyDoctorApiClient.instanceMessage.deleteMessages(id = id)
            .enqueue(object : Callback<Unit> {
                override fun onResponse(
                    call: Call<Unit>,
                    response: Response<Unit>
                ) {
                    val code = response.code()
                    val body = response.body()

                    if (code == 200) {
                        loadDataAPI()
                    } else {
                        showErrorMessage("Gagal Mengambil data dari API")
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    showErrorMessage(t.message)
                }
            })
    }

    // function untuk mengupdate data pada api
    private fun updateDataAPI(id: String, message: MessagesRequest) {
        MyDoctorApiClient.instanceMessage.updateMessages(id = id, request = message)
            .enqueue(object : Callback<MessagesResponse> {
                override fun onResponse(
                    call: Call<MessagesResponse>,
                    response: Response<MessagesResponse>
                ) {
                    val code = response.code()
                    val body = response.body()

                    if (code == 200) {
                        loadDataAPI()
                    } else {
                        showErrorMessage("Gagal Mengambil data dari API")
                    }
                }

                override fun onFailure(call: Call<MessagesResponse>, t: Throwable) {
                    showErrorMessage(t.message)
                }
            })
    }

    private fun showErrorMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        MyDoctorDatabase.destroyInstance()
    }
}