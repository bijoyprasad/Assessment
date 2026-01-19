package com.bijoy.qbytezassessment.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.bijoy.qbytezassessment.R
import com.bijoy.qbytezassessment.databinding.PricingViewBinding

class CustomPricingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = PricingViewBinding.bind(View.inflate(context, R.layout.pricing_view, this))

    var price: Double = 0.0
        set(value) {
            field = value
            binding.price.text = value.toString()
        }

    var currencyTextSizeSp: Float = 12f
        set(value) {
            field = value
            binding.currency.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                value
            )
        }

    var priceTextSize: Float = 20f
        set(value) {
            field = value
            binding.price.setTextSize(
                TypedValue.COMPLEX_UNIT_SP,
                value
            )
        }



}