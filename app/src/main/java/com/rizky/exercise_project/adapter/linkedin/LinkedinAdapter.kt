package com.rizky.exercise_project.adapter.linkedin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.ViewSizeResolver
import coil.transform.CircleCropTransformation
import com.rizky.exercise_project.R
import com.rizky.exercise_project.databinding.ListItemLinkedinBinding
import com.rizky.exercise_project.model.linkedin.Linkedin

class LinkedinAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var oldCategory: MutableList<Linkedin> = mutableListOf()

    val differ: (List<Linkedin>) -> Unit = {
        val diffUtil = DiffUtilCategory(oldCategory, it)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldCategory.clear()
        oldCategory.addAll(it)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(private val binding: ListItemLinkedinBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Linkedin) {
            binding.imgPersonPicture.load(item.img_picture_person) {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_image_24)
                transformations(CircleCropTransformation())
                size(ViewSizeResolver(binding.imgPersonPicture))
            }
            binding.imgProfilePicture.load(item.img_profile_picture) {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_image_24)
                transformations(CircleCropTransformation())
                size(ViewSizeResolver(binding.imgProfilePicture))
            }
            binding.imgPost.load(item.img_post) {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_image_24)
                size(ViewSizeResolver(binding.imgPost))
            }
            binding.imgReaction.load(item.img_reaction?.let {
                ResourcesCompat.getDrawable(itemView.resources,
                    it, null)
            }) {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_image_24)
                size(ViewSizeResolver(binding.imgReaction))
            }
            binding.txtPersonLike.text = item.like_person?.let { itemView.resources.getString(it) }
            binding.txtNamePerson.text = item.name_person
            binding.txtMotto.text = item.motto
            binding.txtLastSend.text = item.last_send
            binding.txtDescription.text = item.description
            binding.txtReaction.text = item.reaction?.let { itemView.resources.getText(it) }
            binding.txtLikeComment.text = item.like_comment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            ListItemLinkedinBinding.inflate(
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
    private val oldCategory: List<Linkedin>,
    private val newCategory: List<Linkedin>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldCategory.size
    }

    override fun getNewListSize(): Int {
        return newCategory.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCategory[oldItemPosition].id_post == newCategory[newItemPosition].id_post
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCategory[oldItemPosition] == newCategory[newItemPosition]
    }
}