package com.rizky.exercise_project.adapter.doctor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.ViewSizeResolver
import coil.transform.CircleCropTransformation
import com.rizky.exercise_project.R
import com.rizky.exercise_project.databinding.ListItemDoctor1Binding
import com.rizky.exercise_project.model.doctor.Category

class CategoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var oldCategory: MutableList<Category> = mutableListOf()

    val differ: (List<Category>) -> Unit = {
        val diffUtil = DiffUtilCategory(oldCategory, it)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldCategory.clear()
        oldCategory.addAll(it)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(val binding: ListItemDoctor1Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Category) {
            binding.txtName.text = item.name
            binding.imgView.load(item.image) {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_image_24)
                transformations(CircleCropTransformation())
                size(ViewSizeResolver(binding.imgView))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ListItemDoctor1Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        holder.bind(oldCategory[position])
    }

    override fun getItemCount(): Int {
        return oldCategory.size
    }
}

class DiffUtilCategory(
    private val oldCategory: List<Category>,
    private val newCategory: List<Category>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldCategory.size
    }

    override fun getNewListSize(): Int {
        return newCategory.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCategory[oldItemPosition].name == newCategory[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldCategory[oldItemPosition].name != newCategory[newItemPosition].name -> {
                false
            }
            oldCategory[oldItemPosition].image != newCategory[newItemPosition].image -> {
                false
            }
            else -> true
        }
    }
}