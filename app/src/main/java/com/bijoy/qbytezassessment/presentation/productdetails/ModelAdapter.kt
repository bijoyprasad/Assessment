package com.bijoy.qbytezassessment.presentation.productdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bijoy.qbytezassessment.data.model.ProductModel
import com.bijoy.qbytezassessment.data.model.ProductSku
import com.bijoy.qbytezassessment.databinding.AdapterProductSkuBinding

class ModelAdapter(
    private val onModelSelected: (ProductModel) -> Unit
) : RecyclerView.Adapter<ModelAdapter.ModelVH>() {

    private val items = mutableListOf<ProductModel>()
    private var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelVH {
        val binding = AdapterProductSkuBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ModelVH(binding)
    }

    override fun onBindViewHolder(holder: ModelVH, position: Int) {
        val model = items[position]

        holder.binding.tvTitle.text = model.name
        holder.binding.tvSubtitle.text = model.display
        holder.binding.root.isSelected = position == selectedPosition

        holder.binding.root.setOnClickListener {
            if (selectedPosition == position) return@setOnClickListener

            val old = selectedPosition
            selectedPosition = position

            if (old != RecyclerView.NO_POSITION) notifyItemChanged(old)
            notifyItemChanged(position)

            onModelSelected(model)
        }
    }

    inner class ModelVH(val binding: AdapterProductSkuBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount() = items.size

    fun submitList(newModels: List<ProductModel>) {
        items.clear()
        items.addAll(newModels)
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

    fun getSelectedItem(): ProductModel? =
        if (selectedPosition in items.indices) items[selectedPosition] else null
}