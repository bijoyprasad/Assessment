package com.bijoy.qbytezassessment.presentation.productdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bijoy.qbytezassessment.data.model.Storage
import com.bijoy.qbytezassessment.databinding.AdapterProductSkuBinding

class StorageAdapter(
    private val onItemClick: (Storage) -> Unit
) : RecyclerView.Adapter<StorageAdapter.StorageVH>() {

    private val items = mutableListOf<Storage>()
    private var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageVH {
        val binding = AdapterProductSkuBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StorageVH(binding)
    }

    override fun onBindViewHolder(holder: StorageVH, position: Int) {
        val storage = items[position]
        holder.binding.tvTitle.text = storage.name
        holder.binding.tvSubtitle.isVisible = false
        holder.binding.root.isSelected = position == selectedPosition

        holder.binding.root.setOnClickListener {
            if (selectedPosition == position) return@setOnClickListener

            val old = selectedPosition
            selectedPosition = position

            if (old != RecyclerView.NO_POSITION) notifyItemChanged(old)
            notifyItemChanged(position)

            onItemClick(storage)
        }
    }

    inner class StorageVH(val binding: AdapterProductSkuBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = items.size

    fun submitList(newStorages: List<Storage>) {
        items.clear()
        items.addAll(newStorages)
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

    fun getSelectedItem(): Storage? =
        if (selectedPosition in items.indices) items[selectedPosition] else null
}