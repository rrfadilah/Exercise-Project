package com.rizky.exercise_project.ui.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.R
import com.rizky.exercise_project.adapter.messages.MessagesAdapter
import com.rizky.exercise_project.databinding.FragmentMessagesBinding
import com.rizky.exercise_project.model.messages.MessageModel

class MessagesFragment : Fragment() {
    private lateinit var rv_mydatapesan: RecyclerView

    private var _binding: FragmentMessagesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMessagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_mydatapesan = requireView().findViewById(R.id.rv_message)
        rv_mydatapesan.setHasFixedSize(true)
        listpesan.addAll(getListMyDataMessages())
        showRecyclerCardView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val listpesan = ArrayList<MessageModel>()

    fun getListMyDataMessages(): ArrayList<MessageModel> {
        val datagambar = resources.getStringArray(R.array.data_image_pesan)
        val datanama = resources.getStringArray(R.array.data_nama)
        val datapesan = resources.getStringArray(R.array.data_pesan)

        val listMyData = ArrayList<MessageModel>()
        for (position in datanama.indices) {
            val myDatapesan = MessageModel(
                datagambar[position],
                datanama[position],
                datapesan[position]
            )
            listMyData.add(myDatapesan)
        }
        return listMyData
    }

    private fun showRecyclerCardView() {
        rv_mydatapesan.layoutManager = LinearLayoutManager(context)
        val cardViewMyDataAdapter = MessagesAdapter(listpesan)
        rv_mydatapesan.adapter = cardViewMyDataAdapter
    }
}