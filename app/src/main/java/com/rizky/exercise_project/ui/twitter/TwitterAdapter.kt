package com.rizky.exercise_project.ui.twitter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import coil.size.ViewSizeResolver
import coil.transform.CircleCropTransformation
import com.rizky.exercise_project.R

class TwitterAdapter (private val listMyTwitter: ArrayList<TwitterModel>) :
    RecyclerView.Adapter<TwitterAdapter.CardViewViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_twitter, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val myDataTwitter = listMyTwitter[position]
        holder.imgprofile.load(myDataTwitter.profile){
            crossfade(true)
            placeholder(R.drawable.ic_baseline_image_24)
            transformations(CircleCropTransformation())
            size(ViewSizeResolver(holder.imgprofile))
        }
        holder.tvnamatwit.text = myDataTwitter.namatwitter
        holder.tvusername.text = myDataTwitter.username
        holder.tvtime.text = myDataTwitter.time
        holder.tvpesantwit.text = myDataTwitter.messagetwitter
        holder.imggambar.load(myDataTwitter.picture){
            crossfade(true)
            placeholder(R.drawable.ic_baseline_image_24)
            transformations(CircleCropTransformation())
            size(ViewSizeResolver(holder.imggambar))
        }
        holder.tvcomment.text = myDataTwitter.comment
        holder.tvretweet.text = myDataTwitter.retweet
        holder.tvlike.text = myDataTwitter.like
    }

    override fun getItemCount(): Int {
        return listMyTwitter.size
    }


    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgprofile: ImageView = itemView.findViewById(R.id.iv_profile_picture)
        var tvnamatwit: TextView = itemView.findViewById(R.id.tv_name_twitter)
        var tvusername: TextView = itemView.findViewById(R.id.tv_username)
        var tvtime: TextView = itemView.findViewById(R.id.tv_time)
        var tvpesantwit: TextView = itemView.findViewById(R.id.tv_message_twitter)
        var imggambar : ImageView = itemView.findViewById(R.id.iv_gambar_twitter)
        var tvcomment : TextView = itemView.findViewById(R.id.tv_comment)
        var tvretweet : TextView = itemView.findViewById(R.id.tv_retweet)
        var tvlike : TextView = itemView.findViewById(R.id.tv_like)
    }
}