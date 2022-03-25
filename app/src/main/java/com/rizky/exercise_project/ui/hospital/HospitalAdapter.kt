package com.rizky.exercise_project.ui.hospital

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

class HospitalAdapter(private val listMyDatas: ArrayList<MyData>)  :
    RecyclerView.Adapter<HospitalAdapter.CardViewViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hospital, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val myData = listMyDatas[position]
        /*Glide.with(holder.itemView.context)
            .load(myData.image)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)*/
        holder.imgPhoto.load(myData.image) {
            crossfade(true)
            placeholder(R.drawable.ic_baseline_image_24)
            transformations(RoundedCornersTransformation(11f))
            size(ViewSizeResolver(holder.imgPhoto))
        }
        holder.tvTitle.text = myData.title
        holder.tvAddress.text = myData.address
    }

    override fun getItemCount(): Int {
        return listMyDatas.size
    }


    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView =
            itemView.findViewById(R.id.ivCardHospital)
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitleCardHospital)
        var tvAddress: TextView = itemView.findViewById(R.id.tvAddressCardHospital)
    }
}