package com.rizky.exercise_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.ViewSizeResolver
import coil.transform.CircleCropTransformation
import com.rizky.exercise_project.R
import com.rizky.exercise_project.databinding.ListItemDoctor2Binding
import com.rizky.exercise_project.model.TopRateDoctor

class TopRateDoctorAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var oldTopRateDoctor: MutableList<TopRateDoctor> = mutableListOf()

    val differ: (List<TopRateDoctor>) -> Unit = {
        val diffUtil = DiffUtilTopRateDoctor(oldTopRateDoctor, it)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldTopRateDoctor.clear()
        oldTopRateDoctor.addAll(it)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(val binding: ListItemDoctor2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TopRateDoctor) {
            binding.imgView.load(item.image) {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_image_24)
                transformations(CircleCropTransformation())
                size(ViewSizeResolver(binding.imgView))
            }
            binding.doctorName.text = item.doctorName
            binding.specialist.text = item.specialist
            item.rating?.let {
                binding.rating.rating = it
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ListItemDoctor2Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        val items = oldTopRateDoctor[position]
        holder.bind(items)
    }

    override fun getItemCount(): Int {
        return oldTopRateDoctor.size
    }
}

class DiffUtilTopRateDoctor(
    private val oldTopRateDoctor: List<TopRateDoctor>,
    private val newTopRateDoctor: List<TopRateDoctor>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldTopRateDoctor.size
    }

    override fun getNewListSize(): Int {
        return newTopRateDoctor.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTopRateDoctor[oldItemPosition].doctorName == newTopRateDoctor[newItemPosition].doctorName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldTopRateDoctor[oldItemPosition].image != newTopRateDoctor[newItemPosition].image -> {
                false
            }
            oldTopRateDoctor[oldItemPosition].specialist != newTopRateDoctor[newItemPosition].specialist -> {
                false
            }
            oldTopRateDoctor[oldItemPosition].rating != newTopRateDoctor[newItemPosition].rating -> {
                false
            }
            else -> true
        }
    }
}