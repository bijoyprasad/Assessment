package com.bijoy.qbytezassessment.presentation.productdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bijoy.qbytezassessment.databinding.ItemThumbnailBinding

class ThumbnailAdapter(
    private val images: List<Int>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<ThumbnailAdapter.ThumbViewHolder>() {

    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThumbViewHolder {
        val binding = ItemThumbnailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ThumbViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ThumbViewHolder, position: Int) {
        holder.image.setImageResource(images[position])
        holder.image.alpha = if (position == selectedPosition) 1f else 0.5f

        holder.itemView.setOnClickListener {
            selectedPosition = position
            notifyDataSetChanged()
            onClick(position)
        }
    }

    inner class ThumbViewHolder(binding: ItemThumbnailBinding):
        RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.ivThumbnail
    }

    override fun getItemCount() = images.size

    fun setSelected(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }
}