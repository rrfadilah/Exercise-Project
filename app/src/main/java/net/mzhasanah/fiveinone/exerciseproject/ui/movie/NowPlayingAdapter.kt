package net.mzhasanah.fiveinone.exerciseproject.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.mzhasanah.fiveinone.exerciseproject.databinding.ListItemNowplayingBinding
import net.mzhasanah.fiveinone.exerciseproject.model.movie.NowPlayingModel

class NowPlayingAdapter(private var list: List<NowPlayingModel>) :
    RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ListItemNowplayingBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun updateList(list: List<NowPlayingModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemNowplayingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        Glide.with(holder.binding.root.context)
            .load(item.image)
            .into(holder.binding.ivPoster)

        holder.binding.tvTitle.text = item.title
    }

    override fun getItemCount(): Int {
        return list.size
    }

}