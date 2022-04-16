package com.example.exercise_project.Home.Messages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.exercise_project.Home.TaskList
import com.example.exercise_project.R
import com.example.exercise_project.data.API.MessagesRequest
import com.example.exercise_project.data.API.MessagesResponse
import com.example.exercise_project.data.local.MessageEntity
import com.example.exercise_project.database.MyDoctorDatabase
import com.example.exercise_project.databinding.FragmentMessagesBinding
import com.example.exercise_project.network.MyDoctorAPIClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentMessages : Fragment() {
    private var _binding: FragmentMessagesBinding? = null
    private val binding get() = _binding!!

    private var db: MyDoctorDatabase? = null
    private lateinit var adapter: AdapterMessages

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

        adapter = AdapterMessages(
            listener = object : AdapterMessages.EventListener {
                override fun onClick(item: ModelMessages) {
                    Toast.makeText(requireContext(), item.text2, Toast.LENGTH_SHORT).show()
                }

                override fun onDelete(item: ModelMessages) {
//                    val message = MessageEntity(
//                        id = item.id,
//                        name = item.name,
//                        image = item.image,
//                        message = item.lastMessage
//                    )
//                    deleteDataDatabase(message)

                    deleteDataAPI(item.id)
                }

                override fun onUpdate(item: ModelMessages) {
//                    val message = MessageEntity(
//                        id = item.id,
//                        name = item.name + " Updated",
//                        image = item.image,
//                        message = item.lastMessage + " Updated"
//                    )
//                    updateDataDatabase(message)

                    val message = MessagesRequest(
                        name = "#" + item.text1,
                        image = item.image2,
                        message = "# " + item.text2
                    )
                    updateDataAPI(id = item.id, message = message)
                }
            },
            ListMessages = emptyList()
        )

        binding.rvDokterAnak.adapter = adapter

//        loadDataDatabase()
        loadDataAPI()

        binding.fabAdd.setOnClickListener {
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
    fun loadDataDummy(): List<ModelMessages> {
        return listOf(
            ModelMessages(
                id = "1",
                text1 = "Rizky Fadilah",
                image1 = R.drawable.image_doctor1,
                text2 = "Ini Contoh Message nya."
            ),
            ModelMessages(
                id = "2",
                text1 = "Rizky Fadilah 2",
                image1 = R.drawable.image_doctor2,
                text2 = "Ini Contoh Message nya Yang kedua."
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
                results?.let {
                    val messages = it.map {
                        ModelMessages(
                            id = it.id,
                            text1 = it.name,
                            image1 = R.drawable.image_doctor1,
                            image2 = it.image,
                            text2 = it.message
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
        MyDoctorAPIClient.instanceMessage.getMessages()
            .enqueue(object : Callback<List<MessagesResponse>> {
                override fun onResponse(
                    call: Call<List<MessagesResponse>>,
                    response: Response<List<MessagesResponse>>
                ) {
                    val code = response.code()
                    val body = response.body()

                    if (code == 200) {
                        val message = body?.map {
                            ModelMessages(
                                id = it.id.orEmpty(),
                                text1 = it.name.orEmpty(),
                                image1 = R.drawable.image_doctor1,
                                image2 = it.image.orEmpty(),
                                text2 = it.message.orEmpty()
                            )
                        } ?: emptyList()
                        adapter.updateList(message.sortedBy { it.text1 })
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
        MyDoctorAPIClient.instanceMessage.postMessages(request = message)
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
        MyDoctorAPIClient.instanceMessage.deleteMessages(id = id)
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
        MyDoctorAPIClient.instanceMessage.updateMessages(id = id, request = message)
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