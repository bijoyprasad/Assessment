package com.bijoy.qbytezassessment.presentation.productdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bijoy.qbytezassessment.data.model.ProductImages
import com.bijoy.qbytezassessment.data.model.ProductModel
import com.bijoy.qbytezassessment.databinding.AdapterProductColorBinding

class ColorAdapter(
    private val onColorSelected: (ProductImages) -> Unit
) : RecyclerView.Adapter<ColorAdapter.ColorVH>() {

    private val items = mutableListOf<ProductImages>()
    private var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorVH {
        val binding = AdapterProductColorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ColorVH(binding)
    }

    override fun onBindViewHolder(holder: ColorVH, position: Int) {
        val color = items[position]
        holder.binding.tvName.text = color.title
        holder.binding.ivImage.setImageResource(color.imageList[0])
        holder.binding.root.isSelected = position == selectedPosition

        holder.binding.root.setOnClickListener {
            if (selectedPosition == position) return@setOnClickListener
            val old = selectedPosition
            selectedPosition = position
            if (old != RecyclerView.NO_POSITION) notifyItemChanged(old)
            notifyItemChanged(position)
            onColorSelected(color)
        }
    }

    inner class ColorVH(val binding: AdapterProductColorBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = items.size

    fun submitList(newColors: List<ProductImages>) {
        items.clear()
        items.addAll(newColors)
        selectedPosition = RecyclerView.NO_POSITION
        notifyDataSetChanged()
    }

    fun setSelected(position: Int) {
        if (position == selectedPosition) return
        val old = selectedPosition
        selectedPosition = position
        if (old != RecyclerView.NO_POSITION) notifyItemChanged(old)
        notifyItemChanged(position)
    }

    fun getSelectedItem(): ProductImages? =
        if (selectedPosition in items.indices) items[selectedPosition] else null
}