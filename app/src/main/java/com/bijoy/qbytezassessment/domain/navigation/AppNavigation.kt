package com.bijoy.qbytezassessment.domain.navigation

import androidx.navigation.NavController
import com.bijoy.qbytezassessment.data.model.Product

interface AppNavigation {

    fun navigateToProductDetails(navController: NavController, productId: Int)

    fun navigateToCart(navController: NavController)

}