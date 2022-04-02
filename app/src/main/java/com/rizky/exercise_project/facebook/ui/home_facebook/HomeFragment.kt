package com.rizky.exercise_project.facebook.ui.home_facebook

import ReelsFacebookAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.R
import com.rizky.exercise_project.databinding.FragmentHomeFacebookBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeFacebookBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentHomeFacebookBinding.inflate(inflater, container, false)

        val recyclerview = binding.rvReels

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ReelsFacebookModel>()


        // add image and text to the arraylist
        data.add(ReelsFacebookModel(R.drawable.rectangle483, R.drawable.profilephoto))
        data.add(ReelsFacebookModel(R.drawable.rectangle483, R.drawable.profilephoto))
        data.add(ReelsFacebookModel(R.drawable.rectangle483, R.drawable.profilephoto))
        data.add(ReelsFacebookModel(R.drawable.rectangle483, R.drawable.profilephoto))
        data.add(ReelsFacebookModel(R.drawable.rectangle483, R.drawable.profilephoto))
        data.add(ReelsFacebookModel(R.drawable.rectangle483, R.drawable.profilephoto))
        data.add(ReelsFacebookModel(R.drawable.rectangle483, R.drawable.profilephoto))
        data.add(ReelsFacebookModel(R.drawable.rectangle483, R.drawable.profilephoto))


        // This will pass the ArrayList to our Adapter
        val adapter = ReelsFacebookAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        //make the recyclerview horizontal
        recyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)



        val recyclerview2 = binding.rvStatus

        // ArrayList of class ItemsViewModel
        val data2 = ArrayList<StatusFacebookModel>()


        // add image and text to the arraylist
        data2.add(
            StatusFacebookModel(
                R.drawable.personimage,
                R.drawable.ic_group_icon,
                R.drawable.rectangle29,
                R.drawable.ic_like,
                R.drawable.ic_commentar,
                R.drawable.ic_massanger,
                R.drawable.like,
                R.drawable.heart,
                "Deven Mestry",
                "1 h .  Mumbai, Maharastra .",
                "Old is Gold..!!",
                "Liked by Sachin Kamble",
                "9 comments•47 shares",
            )
        )
        data2.add(
            StatusFacebookModel(
                R.drawable.personimage,
                R.drawable.ic_group_icon,
                R.drawable.rectangle29,
                R.drawable.ic_like,
                R.drawable.ic_commentar,
                R.drawable.ic_massanger,
                R.drawable.like,
                R.drawable.heart,
                "Deven Mestry",
                "1 h .  Mumbai, Maharastra .",
                "Old is Gold..!!",
                "Liked by Sachin Kamble",
                "9 comments•47 shares",
            )
        )
        data2.add(
            StatusFacebookModel(
                R.drawable.personimage,
                R.drawable.ic_group_icon,
                R.drawable.rectangle29,
                R.drawable.ic_like,
                R.drawable.ic_commentar,
                R.drawable.ic_massanger,
                R.drawable.like,
                R.drawable.heart,
                "Deven Mestry",
                "1 h .  Mumbai, Maharastra .",
                "Old is Gold..!!",
                "Liked by Sachin Kamble",
                "9 comments•47 shares",
            )
        )
        data2.add(
            StatusFacebookModel(
                R.drawable.personimage,
                R.drawable.ic_group_icon,
                R.drawable.rectangle29,
                R.drawable.ic_like,
                R.drawable.ic_commentar,
                R.drawable.ic_massanger,
                R.drawable.like,
                R.drawable.heart,
                "Deven Mestry",
                "1 h .  Mumbai, Maharastra .",
                "Old is Gold..!!",
                "Liked by Sachin Kamble",
                "9 comments•47 shares",
            )
        )
        data2.add(
            StatusFacebookModel(
                R.drawable.personimage,
                R.drawable.ic_group_icon,
                R.drawable.rectangle29,
                R.drawable.ic_like,
                R.drawable.ic_commentar,
                R.drawable.ic_massanger,
                R.drawable.like,
                R.drawable.heart,
                "Deven Mestry",
                "1 h .  Mumbai, Maharastra .",
                "Old is Gold..!!",
                "Liked by Sachin Kamble",
                "9 comments•47 shares",
            )
        )



        // This will pass the ArrayList to our Adapter
        val adapter2 = StatusFacebookAdapter(data2)

        // Setting the Adapter with the recyclerview
        recyclerview2.adapter = adapter2

        //set recyclerview2 to vertical
        recyclerview2.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerview2.isNestedScrollingEnabled = false

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}