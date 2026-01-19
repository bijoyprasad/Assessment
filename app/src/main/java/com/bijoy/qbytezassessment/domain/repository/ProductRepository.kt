package com.bijoy.qbytezassessment.domain.repository

import com.bijoy.qbytezassessment.data.model.Product
import com.bijoy.qbytezassessment.data.model.ProductSku

interface ProductRepository {

    suspend fun getProduct(id: Int): Product

    suspend fun getProductSku(productId: Int): ProductSku?

}