package com.bijoy.qbytezassessment.data.navigation

import androidx.navigation.NavController
import com.bijoy.qbytezassessment.data.model.Product
import com.bijoy.qbytezassessment.domain.navigation.AppNavigation
import com.bijoy.qbytezassessment.presentation.dashboard.DashboardFragmentDirections

class AppNavigationImpl: AppNavigation {

    override fun navigateToProductDetails(navController: NavController, productId: Int) {
        navController.navigate(
            DashboardFragmentDirections.actionDashboardToProduct(productId))
    }

    override fun navigateToCart(navController: NavController) {

    }
}