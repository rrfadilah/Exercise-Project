package com.rizky.exercise_project.ui.linkedin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.size.ViewSizeResolver
import coil.transform.CircleCropTransformation
import com.rizky.exercise_project.MainActivity
import com.rizky.exercise_project.R
import com.rizky.exercise_project.adapter.linkedin.LinkedinAdapter
import com.rizky.exercise_project.databinding.FragmentLinkedinBinding
import com.rizky.exercise_project.model.linkedin.Linkedin

class LinkedinFragment : Fragment() {

    private var _binding: FragmentLinkedinBinding? = null
    private val binding get() = _binding!!
    private val adapter: LinkedinAdapter by lazy { LinkedinAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLinkedinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgView.load(ResourcesCompat.getDrawable(resources, R.drawable.alexander, null)) {
            crossfade(true)
            placeholder(R.drawable.ic_baseline_image_24)
            transformations(CircleCropTransformation())
            size(ViewSizeResolver(binding.imgView))
        }
        adapter.differ(ListItems.dataDummy())
        binding.rvLinkedin.adapter = adapter
        binding.rvLinkedin.setHasFixedSize(true)
        binding.rvLinkedin.layoutManager = LinearLayoutManager((activity as MainActivity))
        binding.rvLinkedin.itemAnimator = DefaultItemAnimator()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

object ListItems {
    fun dataDummy(): MutableList<Linkedin> {
        return mutableListOf(
            Linkedin(
                id_post = 1,
                img_picture_person = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
                img_profile_picture = "https://picsum.photos/200",
                like_person = R.string.txt_person_like,
                name_person = "Arya Rezza",
                motto = "Maju terus pantang mundur",
                last_send = "1 minggu lalu",
                description = "Yeay dapat sertifikat binar academy cuy!",
                img_post = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
                img_reaction = R.drawable.ic_baseline_thumb_up_24,
                reaction = R.string.txt_reaction,
                like_comment = "240 Menyukai • 10 Berkomentar"
            ),
            Linkedin(
                id_post = 2,
                img_picture_person = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
                img_profile_picture = "https://picsum.photos/200",
                like_person = R.string.txt_person_like,
                name_person = "Rezza Arya",
                motto = "Hello world 🚀",
                last_send = "1 hari lalu",
                description = "Hello world to Binar Academy gaes",
                img_post = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
                img_reaction = R.drawable.ic_baseline_thumb_up_24,
                reaction = R.string.txt_reaction,
                like_comment = "12 Menyukai • 5 Berkomentar"
            ),
            Linkedin(
                id_post = 3,
                img_picture_person = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
                img_profile_picture = "https://picsum.photos/200",
                like_person = R.string.txt_person_like,
                name_person = "Anantya Arya",
                motto = "Newbie!",
                last_send = "1 jam lalu",
                description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.",
                img_post = "https://i.ibb.co/zJHYGBP/binarlogo.jpg",
                img_reaction = R.drawable.ic_baseline_thumb_up_24,
                reaction = R.string.txt_reaction,
                like_comment = "1 Berkomentar"
            )
        )
    }
}

