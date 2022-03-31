package com.rizky.exercise_project.hospitals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rizky.exercise_project.R

class AdapterHospital(private val listhospital: ArrayList<ModelHospital>) : RecyclerView.Adapter<AdapterHospital.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_hospitals, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (Image, Title, Description) = listhospital[position]
        holder.ivHospital1.setImageResource(Image)
        holder.tvNameHospital.text = Title
        holder.tvAddressHospital.text = Description
    }

    override fun getItemCount(): Int = listhospital.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivHospital1: ImageView = itemView.findViewById(R.id.iv_hospital1)
        var tvNameHospital: TextView = itemView.findViewById(R.id.tv_nameHospital)
        var tvAddressHospital: TextView = itemView.findViewById(R.id.tv_addressHospital)
    }
}