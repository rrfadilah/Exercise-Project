package com.rizky.exercise_project.ui.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.ViewSizeResolver
import coil.transform.CircleCropTransformation
import com.rizky.exercise_project.MainActivity
import com.rizky.exercise_project.R
import com.rizky.exercise_project.adapter.CategoryAdapter
import com.rizky.exercise_project.adapter.NewsAdapter
import com.rizky.exercise_project.adapter.TopRateDoctorAdapter
import com.rizky.exercise_project.databinding.FragmentDoctorBinding
import com.rizky.exercise_project.model.Category
import com.rizky.exercise_project.model.News
import com.rizky.exercise_project.model.TopRateDoctor
import com.rizky.exercise_project.utils.dividerVertical

class DoctorFragment : Fragment() {

    private lateinit var adapter1: CategoryAdapter
    private lateinit var adapter2: TopRateDoctorAdapter
    private lateinit var adapter3: NewsAdapter
    private var listItem1: MutableList<Category> = mutableListOf()
    private var listItem2: MutableList<TopRateDoctor> = mutableListOf()
    private var listItem3: MutableList<News> = mutableListOf()
    private var _binding: FragmentDoctorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDoctorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listItem1 = ListItems.listItem1()
        listItem2 = ListItems.listItem2()
        listItem3 = ListItems.listItem3()
        adapter1 = CategoryAdapter()
        adapter2 = TopRateDoctorAdapter()
        adapter3 = NewsAdapter()
        onBinding()
        onListAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onBinding() {
        binding.imgView.load("https://i.ibb.co/zJHYGBP/binarlogo.jpg") {
            crossfade(true)
            placeholder(R.drawable.ic_baseline_image_24)
            transformations(CircleCropTransformation())
            size(ViewSizeResolver(binding.imgView))
        }
    }

    private fun onListAdapter() {
        adapter1.differ(listItem1)
        adapter2.differ(listItem2)
        adapter3.differ(listItem3)

        binding.rvListItem1.adapter = adapter1
        binding.rvListItem1.setHasFixedSize(true)
        binding.rvListItem1.layoutManager = LinearLayoutManager((activity as MainActivity), RecyclerView.HORIZONTAL, false)
        binding.rvListItem1.itemAnimator = DefaultItemAnimator()

        binding.rvListItem2.adapter = adapter2
        binding.rvListItem2.setHasFixedSize(true)
        binding.rvListItem2.layoutManager = LinearLayoutManager((activity as MainActivity))
        binding.rvListItem2.itemAnimator = DefaultItemAnimator()

        binding.rvListItem3.adapter = adapter3
        binding.rvListItem3.setHasFixedSize(true)
        binding.rvListItem3.layoutManager = LinearLayoutManager((activity as MainActivity))
        binding.rvListItem3.itemAnimator = DefaultItemAnimator()
        binding.rvListItem3.addItemDecoration(dividerVertical((activity as MainActivity), 0, 0))
    }
}

object ListItems{
    fun listItem1(): MutableList<Category> {
        return mutableListOf(
            Category(
                image = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
                name = "Dukun santet"
            ),
            Category(
                image = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
                name = "Dukun sunat"
            ),
            Category(
                image = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
                name = "Doctor cinta"
            )
        )
    }

    fun listItem2(): MutableList<TopRateDoctor> {
        return mutableListOf(
            TopRateDoctor(
                image = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
                doctorName = "Arya Rezza",
                specialist = "Pediatrician",
                rating = 4.5f
            ),
            TopRateDoctor(
                image = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
                doctorName = "Ronaldo Nazario",
                specialist = "Dentist",
                rating = 4.1f
            ),
            TopRateDoctor(
                image = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
                doctorName = "Poe Minn",
                specialist = "Podiatrist",
                rating = 5f
            )
        )
    }

    fun listItem3(): MutableList<News> {
        return mutableListOf(
            News(
                image = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
                title = "Is it safe to stay at home during coronavirus?",
                calendar = "Today"
            ),
            News(
                image = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
                title = "Consume yellow citrus helps you healthier",
                calendar = "Today"
            ),
            News(
                image = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
                title = "Learn how to make a proper orange juice at home",
                calendar = "Today"
            )
        )
    }
}