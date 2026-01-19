package com.bijoy.qbytezassessment.presentation.cart

import android.R.attr.text
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bijoy.qbytezassessment.data.model.CartItem
import com.bijoy.qbytezassessment.data.model.ModifierItem
import com.bijoy.qbytezassessment.data.model.Product
import com.bijoy.qbytezassessment.data.model.ProductType
import com.bijoy.qbytezassessment.databinding.AdapterCartBinding
import com.bijoy.qbytezassessment.databinding.AdapterDashboardBinding
import com.bijoy.qbytezassessment.databinding.AdapterModifierPricingBinding
import com.bijoy.qbytezassessment.databinding.CartModifierPricingBinding
import com.bijoy.qbytezassessment.presentation.ui.CustomCounterView
import com.bijoy.qbytezassessment.presentation.ui.CustomPricingView

class CartAdapter: ListAdapter<CartItem, CartAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: AdapterCartBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val modifierContainer = binding.modifierPricingLayout

        fun bind(item: CartItem) {
            binding.tvName.text = item.name
            binding.itemPrice.price = item.price
            binding.ivImage.setImageResource(item.imagePath)
            binding.customCounterView.count = item.quantity

            binding.customCounterView.onQuantityChange = {
                item.quantity = it
                binding.itemPrice.price = item.price * it
            }

            if (item.productType == ProductType.MOBILE) {
                modifierContainer.removeAllViews()

                item.modifierItems.forEach { modifier ->
                    val modifierView = inflateModifierView(modifier)
                    modifierContainer.addView(modifierView)
                }
            }
        }

        private fun inflateModifierView(
            modifier: ModifierItem
        ): View {
            val inflater = LayoutInflater.from(modifierContainer.context)
           val adapterBinding = CartModifierPricingBinding.inflate(inflater)

            adapterBinding.title.text = modifier.name
            adapterBinding.price.price = modifier.price
            adapterBinding.counterView.count = modifier.quantity

            adapterBinding.counterView.onQuantityChange = {
                modifier.quantity = it
                adapterBinding.price.price = modifier.price * it
            }

            return adapterBinding.root
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<CartItem>() {
            override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem) = oldItem.productId == newItem.productId

            override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem) = oldItem == newItem
        }
    }

}