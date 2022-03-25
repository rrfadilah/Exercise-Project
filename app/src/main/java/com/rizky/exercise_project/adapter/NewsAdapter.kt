package com.rizky.exercise_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.ViewSizeResolver
import coil.transform.RoundedCornersTransformation
import com.rizky.exercise_project.R
import com.rizky.exercise_project.databinding.ListItemDoctor3Binding
import com.rizky.exercise_project.model.News

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var oldNews: MutableList<News> = mutableListOf()

    val differ: (List<News>) -> Unit = {
        val diffUtil = DiffUtilNews(oldNews, it)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldNews.clear()
        oldNews.addAll(it)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(val binding: ListItemDoctor3Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: News) {
            binding.imgView.load(item.image) {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_image_24)
                transformations(RoundedCornersTransformation(11f))
                size(ViewSizeResolver(binding.imgView))
            }
            binding.txtTitle.text = item.title
            binding.txtToday.text = item.calendar
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ListItemDoctor3Binding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        holder.bind(oldNews[position])
    }

    override fun getItemCount(): Int {
        return oldNews.size
    }
}

class DiffUtilNews(
    private val oldNews: List<News>,
    private val newNews: List<News>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldNews.size
    }

    override fun getNewListSize(): Int {
        return newNews.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNews[oldItemPosition].title == newNews[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldNews[oldItemPosition].title != newNews[newItemPosition].title -> {
                false
            }
            oldNews[oldItemPosition].image != newNews[newItemPosition].image -> {
                false
            }
            oldNews[oldItemPosition].calendar != newNews[newItemPosition].calendar -> {
                false
            }
            else -> true
        }
    }
}