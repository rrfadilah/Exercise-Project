package com.rizky.exercise_project.konsultasi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rizky.exercise_project.R

class KonsultasiAdapter(private val list: List<KonsultasiModel>) : RecyclerView.Adapter<KonsultasiAdapter.ViewHolder>()  {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ic_konsul = itemView.findViewById<ImageView>(R.id.iv_ic_konsul)
        val txt_konsul = itemView.findViewById<TextView>(R.id.iv_txt_konsul)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.list_item_konsultasi, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: KonsultasiAdapter.ViewHolder, position: Int) {
        val konsul: KonsultasiModel = list[position]


        holder.ic_konsul.setImageResource(konsul.ic_konsultasi)
        holder.txt_konsul.text = konsul.txt_konsultasi
    }

    override fun getItemCount(): Int {
        return  list.count()
    }
}