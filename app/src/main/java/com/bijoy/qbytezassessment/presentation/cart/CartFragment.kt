package com.bijoy.qbytezassessment.presentation.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bijoy.qbytezassessment.R
import com.bijoy.qbytezassessment.databinding.FragmentCartBinding
import com.bijoy.qbytezassessment.presentation.dashboard.DashboardAdapter
import com.bijoy.qbytezassessment.presentation.productdetails.ProductViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.getValue

class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var binding: FragmentCartBinding

    private val viewModel: CartViewModel by viewModel()

    private val cartAdapter = CartAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCart.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launch {
            viewModel.cartFlow.collect {
                if (it.isEmpty()) {
                    binding.rvCart.visibility = View.GONE
                    binding.tvEmptyCart.visibility = View.VISIBLE
                } else {
                    binding.rvCart.visibility = View.VISIBLE
                    binding.tvEmptyCart.visibility = View.GONE
                }

                cartAdapter.submitList(it)
            }
        }
    }
}