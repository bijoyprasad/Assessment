package com.bijoy.qbytezassessment.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bijoy.qbytezassessment.R
import com.bijoy.qbytezassessment.databinding.CartModifierPricingBinding

class CustomCartModifierPricing @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding =
        CartModifierPricingBinding.bind(View.inflate(context, R.layout.cart_modifier_pricing, this))

    var title: String = ""
        set(value) {
            field = value
            binding.title.text = value
        }

    var price: Double = 0.0
        set(value) {
            field = value
            binding.price.price = value
        }
}