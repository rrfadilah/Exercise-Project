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
import coil.transform.RoundedCornersTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rizky.exercise_project.R
import com.rizky.exercise_project.ui.messages.MessageModel

class MessagesAdapter(private val listMyDataMessages: ArrayList<MessageModel>)  :
    RecyclerView.Adapter<MessagesAdapter.CardViewViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_messages, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val myDatamessages = listMyDataMessages[position]
        Glide.with(holder.itemView.context)
            .load(myDatamessages.gambar)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)
//        holder.imgPhoto.load(myDatamessages.gambar) {
//            crossfade(true)
//            placeholder(R.drawable.ic_baseline_image_24)
//            transformations(RoundedCornersTransformation(11f))
//            size(ViewSizeResolver(holder.imgPhoto))
//        }
        holder.tvnama.text = myDatamessages.name
        holder.tvpesan.text = myDatamessages.lastMessage
    }

    override fun getItemCount(): Int {
        return listMyDataMessages.size
    }


    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView =
            itemView.findViewById(R.id.ivCardMessages)
        var tvnama: TextView = itemView.findViewById(R.id.tvnamaCardMessages)
        var tvpesan: TextView = itemView.findViewById(R.id.tvCardMessages)
    }
}