package com.bijoy.qbytezassessment.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bijoy.qbytezassessment.R
import com.bijoy.qbytezassessment.databinding.CounterViewBinding

class CustomCounterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = CounterViewBinding.bind(View.inflate(context, R.layout.counter_view, this))

    var onQuantityChange: ((Int) -> Unit) = {}

    var buttonColor: Int = R.color.light_grey
        set(value) {
            field = value
            binding.btnPlus.setBackgroundColor(
                ContextCompat.getColor(binding.root.context, value)
            )
            binding.btnMinus.setBackgroundColor(
                ContextCompat.getColor(binding.root.context, value)
            )
        }

    var count: Int = 0
        set(value) {
            field = value
            binding.count.text = value.toString()
        }

    init {
        binding.btnPlus.setOnClickListener {
            count += 1
            binding.count.text = count.toString()
            onQuantityChange.invoke(count)
        }

        binding.btnMinus.setOnClickListener {
            if (count > 1) {
                count -= 1
                binding.count.text = count.toString()
                onQuantityChange.invoke(count)
            }
        }
    }
}