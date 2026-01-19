package com.bijoy.qbytezassessment.presentation.dashboard

import android.R.attr.columnCount
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bijoy.qbytezassessment.R
import com.bijoy.qbytezassessment.data.di.viewModelModule
import com.bijoy.qbytezassessment.data.model.Product
import com.bijoy.qbytezassessment.databinding.FragmentDashboardBinding
import com.bijoy.qbytezassessment.domain.navigation.AppNavigation
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var binding: FragmentDashboardBinding
    private val navigation: AppNavigation by inject()
    private val viewModel: DashboardViewModel by viewModel()
    private val dashboardAdapter = DashboardAdapter(
        onItemClick = ::onDashboardItemClick
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvProduct.adapter = dashboardAdapter
        lifecycleScope.launch {
            viewModel.productList.collect() {
                dashboardAdapter.submitList(it)
            }
        }
    }

    fun onDashboardItemClick(product: Product) {
        navigation.navigateToProductDetails(
            findNavController(),
            product.id
        )
    }

}