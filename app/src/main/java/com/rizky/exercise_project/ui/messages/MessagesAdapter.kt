package com.rizky.exercise_project.ui.messages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.ViewSizeResolver
import coil.transform.CircleCropTransformation
import com.rizky.exercise_project.R

class MessagesAdapter(private val listMyDataMessages: ArrayList<MessageModel>)  :
    RecyclerView.Adapter<MessagesAdapter.CardViewViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_message, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val myDatamessages = listMyDataMessages[position]
        holder.imgPhoto.load(myDatamessages.gambar) {
            crossfade(true)
            placeholder(R.drawable.ic_baseline_image_24)
            transformations(CircleCropTransformation())
            size(ViewSizeResolver(holder.imgPhoto))
        }
        holder.tvnama.text = myDatamessages.name
        holder.tvpesan.text = myDatamessages.lastMessage
    }

    override fun getItemCount(): Int {
        return listMyDataMessages.size
    }


    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.iv_img)
        var tvnama: TextView = itemView.findViewById(R.id.tv_name)
        var tvpesan: TextView = itemView.findViewById(R.id.tv_last_message)
    }
}