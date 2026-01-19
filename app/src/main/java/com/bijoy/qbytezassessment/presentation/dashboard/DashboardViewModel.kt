package com.bijoy.qbytezassessment.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bijoy.qbytezassessment.data.model.Product
import com.bijoy.qbytezassessment.domain.repository.DashboardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val dashboardRepository: DashboardRepository
): ViewModel() {

    private val productListState = MutableStateFlow<List<Product>>(emptyList())
    internal val productList: StateFlow<List<Product>> = productListState.asStateFlow()

    init {
        loadDashboard()
    }

    private fun loadDashboard() {
        viewModelScope.launch {
            dashboardRepository.populateIfEmpty()

            val result = dashboardRepository.getProductList()
            productListState.emit(result)
        }
    }

}