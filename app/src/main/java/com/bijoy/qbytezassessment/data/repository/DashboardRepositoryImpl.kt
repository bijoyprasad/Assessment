package com.bijoy.qbytezassessment.data.repository

import com.bijoy.qbytezassessment.R
import com.bijoy.qbytezassessment.data.database.ProductDao
import com.bijoy.qbytezassessment.data.model.Product
import com.bijoy.qbytezassessment.data.model.ProductSku
import com.bijoy.qbytezassessment.data.model.ProductType
import com.bijoy.qbytezassessment.data.repository.convertor.toDomain
import com.bijoy.qbytezassessment.data.repository.convertor.toEntity
import com.bijoy.qbytezassessment.domain.repository.DashboardRepository

class DashboardRepositoryImpl(
    private val productDao: ProductDao
) : DashboardRepository {

    override suspend fun populateIfEmpty() {
        if (productDao.getCount() == 0)
            insertProducts(dummyProductList())
    }

    private suspend fun insertProducts(products: List<Product>) {
        val entities = products.map { it.toEntity() }
        productDao.insertProducts(entities)
    }

    override suspend fun getProductList(): List<Product> {
        return productDao.getAllProducts().map { it.toDomain() }
    }

    private fun dummyProductList(): List<Product> {
        return listOf(
            Product(
                1,
                "iPhone 16 Pro",
                R.drawable.iphone16_white1,
                140.00,
                ProductType.MOBILE
            ),
            Product(
                2,
                "Lay's American Style Cream & Onion (Potato Chips)",
                R.drawable.lays_green_front,
                40.00,
                ProductType.GROCERY
            ),
            Product(
                3,
                "Lay's West Indies Hot 'n' Sweet Chilli (Potato Chips)",
                R.drawable.lays_orange_front,
                20.00,
                ProductType.GROCERY
            ),
            Product(
                4,
                "Lay's Sizzlin' Hot (Potato Chips)",
                R.drawable.lays_red_front,
                60.00,
                ProductType.GROCERY
            ),
        )
    }

    fun iPhone16Sku() {

    }
}