package com.rizky.exercise_project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * com.rizky.exercise_project
 *
 * Created by Rizky Fadilah on 02/04/22.
 * https://github.com/rizkyfadilah
 *
 */

class PostAdapter(
    private val context: Context,
    private val list: List<PostWrapperModel>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_POST = 1
        const val VIEW_TYPE_ADS = 2
    }

    class ViewHolderPost(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

        }
    }

    class ViewHolderAds(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_POST) {
            ViewHolderPost(
                LayoutInflater.from(context).inflate(R.layout.listitem_post, null, false)
            )
        } else {
            ViewHolderAds(
                LayoutInflater.from(context).inflate(R.layout.listitem_ads, null, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (list[position].viewType == VIEW_TYPE_POST) {
            (holder as ViewHolderPost).bind(position)
        } else {
            (holder as ViewHolderAds).bind(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].viewType
    }
}