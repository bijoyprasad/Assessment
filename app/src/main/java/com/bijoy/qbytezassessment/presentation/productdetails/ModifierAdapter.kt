package com.bijoy.qbytezassessment.presentation.productdetails

import android.R.attr.text
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bijoy.qbytezassessment.data.model.ModifierItem
import com.bijoy.qbytezassessment.databinding.AdapterModifierPricingBinding
import com.bijoy.qbytezassessment.databinding.AdapterProductColorBinding
import com.bijoy.qbytezassessment.databinding.CartModifierPricingBinding

class ModifierAdapter: RecyclerView.Adapter<ModifierAdapter.ViewHolder>() {

    private val items = mutableListOf<ModifierItem>()

    fun submitList(list: List<ModifierItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun getTotalPrice(): Double {
        return items.sumOf { it.price * it.quantity }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterModifierPricingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(val binding: AdapterModifierPricingBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ModifierItem) {
            binding.title.text = item.name
            binding.price.price = item.price
            binding.counterView.count = item.quantity
            binding.counterView.onQuantityChange = {
                item.quantity = it
                binding.price.price = item.price * it
            }
        }
    }

    override fun getItemCount() = items.size

    fun getSelectedModifiers(): List<ModifierItem> {
        return items.filter { it.quantity > 0 }
    }
}