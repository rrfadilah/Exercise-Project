package com.rizky.exercise_project.facebook.ui.home_facebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.R


class StatusFacebookAdapter(private val mList: List<StatusFacebookModel>) :
    RecyclerView.Adapter<StatusFacebookAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_content_facebook, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(itemsViewModel.image1)
        holder.imageView2.setImageResource(itemsViewModel.image2)
        holder.imageView3.setImageResource(itemsViewModel.image3)
        holder.imageView4.setImageResource(itemsViewModel.image4)
        holder.imageView5.setImageResource(itemsViewModel.image5)
        holder.imageView6.setImageResource(itemsViewModel.image6)
        holder.imageView7.setImageResource(itemsViewModel.image7)
        holder.imageView8.setImageResource(itemsViewModel.image8)


        // sets the text to the textview from our itemHolder class
        holder.textView.text = itemsViewModel.text1
        holder.textView2.text = itemsViewModel.text2
        holder.textView3.text = itemsViewModel.text3
        holder.textView4.text = itemsViewModel.text4
        holder.textView5.text = itemsViewModel.text5

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.ivIconProfilDribble)
        val imageView2: ImageView = itemView.findViewById(R.id.iv_group)
        val imageView3: ImageView = itemView.findViewById(R.id.ivImageContent)
        val imageView4: ImageView = itemView.findViewById(R.id.ivIconSuka)
        val imageView5: ImageView = itemView.findViewById(R.id.ivIconComment)
        val imageView6: ImageView = itemView.findViewById(R.id.ivIconSend)
        val imageView7: ImageView = itemView.findViewById(R.id.ivIconLove)
        val imageView8: ImageView = itemView.findViewById(R.id.ivIconInsight)

        val textView: TextView = itemView.findViewById(R.id.tvNameProfilDribble)
        val textView2: TextView = itemView.findViewById(R.id.tvJumlahFollowers)
        val textView3: TextView = itemView.findViewById(R.id.tvTextContent)
        val textView4: TextView = itemView.findViewById(R.id.tvCountAppreciate)
        val textView5: TextView = itemView.findViewById(R.id.tvCountCommentShare)

    }
}