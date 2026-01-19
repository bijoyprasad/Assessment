package com.bijoy.qbytezassessment.domain.repository

import com.bijoy.qbytezassessment.data.model.Product

interface DashboardRepository {

    suspend fun populateIfEmpty()

    suspend fun getProductList(): List<Product>

}