package com.bijoy.qbytezassessment.presentation.productdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bijoy.qbytezassessment.R
import com.bijoy.qbytezassessment.data.model.Product
import com.bijoy.qbytezassessment.databinding.AdapterDashboardBinding
import com.bijoy.qbytezassessment.databinding.AdapterImageSliderBinding

class ImageSliderAdapter(
    private val images: List<Int>
) : RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = AdapterImageSliderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    inner class ImageViewHolder(private val binding: AdapterImageSliderBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Int) {
            binding.ivPagerImage.setImageResource(item)
        }
    }

    override fun getItemCount() = images.size
}