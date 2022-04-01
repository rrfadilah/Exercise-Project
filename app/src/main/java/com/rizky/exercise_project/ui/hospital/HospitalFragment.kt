package com.rizky.exercise_project.ui.hospital

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.R
import com.rizky.exercise_project.adapter.hospital.HospitalAdapter
import com.rizky.exercise_project.databinding.FragmentHospitalBinding
import com.rizky.exercise_project.model.hospital.MyData

class HospitalFragment : Fragment() {
    private lateinit var rv_mydata: RecyclerView

    private var _binding: FragmentHospitalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHospitalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvAvailableHospital: TextView = requireView().findViewById<TextView>(R.id.tvAvailableHospital)
        tvAvailableHospital.text = "${getListMyDatas().size} tersedia"

        rv_mydata = requireView().findViewById(R.id.rv_mydata)
        rv_mydata.setHasFixedSize(true)
        list.addAll(getListMyDatas())
        showRecyclerCardView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val list = ArrayList<MyData>()

    fun getListMyDatas(): ArrayList<MyData> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataAddress = resources.getStringArray(R.array.data_address)
        val dataImage = resources.getStringArray(R.array.data_image)
        val listMyData = ArrayList<MyData>()
        for (position in dataTitle.indices) {
            val myData = MyData(
                dataTitle[position],
                dataAddress[position],
                dataImage[position]
            )
            listMyData.add(myData)
        }
        return listMyData
    }

    private fun showRecyclerCardView() {
        rv_mydata.layoutManager = LinearLayoutManager(context)
        val cardViewMyDataAdapter = HospitalAdapter (list)
        rv_mydata.adapter = cardViewMyDataAdapter
    }
}