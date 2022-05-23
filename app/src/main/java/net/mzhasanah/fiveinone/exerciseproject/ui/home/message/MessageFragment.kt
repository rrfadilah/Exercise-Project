package net.mzhasanah.fiveinone.exerciseproject.ui.home.message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import net.mzhasanah.fiveinone.exerciseproject.R
import net.mzhasanah.fiveinone.exerciseproject.data.api.MessagesRequest
import net.mzhasanah.fiveinone.exerciseproject.data.local.MessageEntity
import net.mzhasanah.fiveinone.exerciseproject.database.MyDoctorDatabase
import net.mzhasanah.fiveinone.exerciseproject.databinding.FragmentMessageBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import net.mzhasanah.fiveinone.exerciseproject.network.MyDoctorApiClient
import net.mzhasanah.fiveinone.exerciseproject.repository.MessageRepository

class MessageFragment : Fragment() {
    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private var db: MyDoctorDatabase? = null
    private lateinit var adapter: MessageAdapter
    private val viewModel: MessageViewModel by viewModels {
        MessageViewModel.Factory(
            MessageRepository(
                MyDoctorApiClient.instanceMessage
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
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
                    viewModel.deleteDataAPI(item.id)
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
                    viewModel.updateDataAPI(id = item.id, message = message)
                }
            },
            list = emptyList()
        )

        binding.rvMessage.adapter = adapter

        bindView()
        bindViewModel()
        viewModel.loadDataAPI()
    }

    private fun bindView() {
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
            viewModel.postDataAPI(message)
        }
    }

    private fun bindViewModel() {
        viewModel.shouldShowData.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }

        viewModel.shouldShowError.observe(viewLifecycleOwner) {
            showErrorMessage(it)
        }
    }

    // function untuk load data dummy
    fun loadDataDummy(): List<MessageModel> {
        return listOf(
            MessageModel(
                id = "1",
                name = "Rizky Fadilah",
                imageRes = R.drawable.img_user_1,
                lastMessage = "Ini Contoh Message nya."
            ),
            MessageModel(
                id = "2",
                name = "Rizky Fadilah 2",
                imageRes = R.drawable.img_user_2,
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
                results?.let {
                    val messages = it.map {
                        MessageModel(
                            id = it.id,
                            name = it.name,
                            imageRes = R.drawable.img_user_1,
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

    private fun showErrorMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        MyDoctorDatabase.destroyInstance()
    }
}