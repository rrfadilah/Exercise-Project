package com.rizky.exercise_project.ui.twitter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.R
import com.rizky.exercise_project.databinding.FragmentTwitterBinding

class TwitterFragment : Fragment() {
    private lateinit var rv_mytwitter: RecyclerView

    private var _binding: FragmentTwitterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTwitterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_mytwitter = requireView().findViewById(R.id.rv_twitter)
        rv_mytwitter.setHasFixedSize(true)
        listtwitter.addAll(getListMyTwitter())
        showRecyclerCardView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val listtwitter = ArrayList<TwitterModel>()

    fun getListMyTwitter(): ArrayList<TwitterModel> {
        val dataprofile = resources.getStringArray(R.array.data_image_twitter)
        val datanamatwitter = resources.getStringArray(R.array.data_nama_twitter)
        val datausername = resources.getStringArray(R.array.data_username_twitter)
        val datatime = resources.getStringArray(R.array.data_time_twitter)
        val datapesantwitter = resources.getStringArray(R.array.data_message_twitter)
        val datagambartwitter = resources.getStringArray(R.array.data_gambar_twitter)
        val datacomment = resources.getStringArray(R.array.data_comment_twitter)
        val datalike = resources.getStringArray(R.array.data_like_twitter)
        val dataretweet = resources.getStringArray(R.array.data_retweet_twitter)

        val listTwitter = ArrayList<TwitterModel>()
        for (position in datanamatwitter.indices) {
            val myDataTwitter = TwitterModel(
                dataprofile[position],
                datanamatwitter[position],
                datausername[position],
                datatime[position],
                datapesantwitter[position],
                datagambartwitter[position],
                datacomment[position],
                dataretweet[position],
                datalike[position]
            )
            listTwitter.add(myDataTwitter)
        }
        return listTwitter
    }

    private fun showRecyclerCardView() {
        rv_mytwitter.layoutManager = LinearLayoutManager(context)
        val cardViewMyDataAdapter = TwitterAdapter(listtwitter)
        rv_mytwitter.adapter = cardViewMyDataAdapter
    }
}
