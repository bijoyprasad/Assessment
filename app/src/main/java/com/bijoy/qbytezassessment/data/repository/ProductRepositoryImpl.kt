package com.bijoy.qbytezassessment.data.repository

import android.util.Log
import com.bijoy.qbytezassessment.R
import com.bijoy.qbytezassessment.data.database.ProductDao
import com.bijoy.qbytezassessment.data.model.Product
import com.bijoy.qbytezassessment.data.model.ProductImages
import com.bijoy.qbytezassessment.data.model.ProductModel
import com.bijoy.qbytezassessment.data.model.ProductSku
import com.bijoy.qbytezassessment.data.model.Storage
import com.bijoy.qbytezassessment.data.repository.convertor.toDomain
import com.bijoy.qbytezassessment.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val productDao: ProductDao
): ProductRepository {

    override suspend fun getProduct(id: Int): Product {
        return productDao.getProduct(id).toDomain()
    }

    override suspend fun getProductSku(productId: Int): ProductSku? {
        return dummyProductSkus().find { it.productId == productId }
    }

    private fun dummyProductSkus(): List<ProductSku> {
        return listOf(
            ProductSku(
                id = 101,
                productId = 1,
                skuIds = listOf(201, 202),
                listOf(
                    ProductModel(
                        skuId = 201,
                        "16 Pro",
                        "15.9cm Display",
                        140.0,
                        listOf(
                            ProductImages(
                                "White",
                                listOf(
                                    R.drawable.iphone16_white1,
                                    R.drawable.iphone16_white2,
                                    R.drawable.iphone16_white3
                                )
                            ),
                            ProductImages(
                                "Blue",
                                listOf(
                                    R.drawable.iphone16_blue1,
                                    R.drawable.iphone16_blue2,
                                    R.drawable.iphone16_blue3
                                )
                            ),
                            ProductImages(
                                "Pink",
                                listOf(
                                    R.drawable.iphone16_pink1,
                                    R.drawable.iphone16_pink2,
                                    R.drawable.iphone16_pink3
                                )
                            ),
                            ProductImages(
                                "Black",
                                listOf(
                                    R.drawable.iphone16_black1,
                                    R.drawable.iphone16_black2,
                                    R.drawable.iphone16_black3
                                )
                            ),

                            ),
                        listOf(
                            Storage("128 GB", 0.0),
                            Storage("256 GB", 20.0),
                        )
                    ),
                    ProductModel(
                        skuId = 202,
                        "16 Pro Max",
                        "17.4cm Display",
                        180.0,
                        listOf(
                            ProductImages(
                                "White Titanium",
                                listOf(
                                    R.drawable.iphone16pro_white1,
                                    R.drawable.iphone16pro_white2,
                                    R.drawable.iphone16pro_white3,
                                    R.drawable.iphone16pro_white4
                                )
                            ),
                            ProductImages(
                                "Desert Titanium",
                                listOf(
                                    R.drawable.iphone16pro_gold1,
                                    R.drawable.iphone16pro_gold2,
                                    R.drawable.iphone16pro_gold3,
                                    R.drawable.iphone16pro_gold4
                                )
                            ),
                            ProductImages(
                                "Natural Titanium",
                                listOf(
                                    R.drawable.iphone16pro_silver1,
                                    R.drawable.iphone16pro_silver2,
                                    R.drawable.iphone16pro_silver3,
                                    R.drawable.iphone16pro_silver4
                                )
                            ),
                            ProductImages(
                                "Black Titanium",
                                listOf(
                                    R.drawable.iphone16pro_black1,
                                    R.drawable.iphone16pro_black2,
                                    R.drawable.iphone16pro_black3,
                                    R.drawable.iphone16pro_black4
                                )
                            ),

                            ),
                        listOf(
                            Storage("256 GB", 0.0),
                            Storage("512 GB", 40.0),
                            Storage("1 TB", 60.0),
                        )
                    )
                )
            ),
            ProductSku(
                id = 102,
                productId = 2,
                emptyList(),
                listOf(
                    ProductModel(
                        skuId = 203,
                        name = "Lays",
                        productImages = listOf(
                            ProductImages(
                                "Green",
                                listOf(
                                    R.drawable.lays_green_front,
                                    R.drawable.lays_green_back
                                )
                            )
                        )
                    )
                )
            ),
            ProductSku(
                id = 103,
                productId = 3,
                emptyList(),
                listOf(
                    ProductModel(
                        skuId = 204,
                        name = "Lays",
                        productImages = listOf(
                            ProductImages(
                                "Orange",
                                listOf(
                                    R.drawable.lays_orange_front,
                                    R.drawable.lays_orange_back
                                )
                            )
                        )
                    )
                )
            ),
            ProductSku(
                id = 104,
                productId = 4,
                emptyList(),
                listOf(
                    ProductModel(
                        skuId = 204,
                        name = "Lays",
                        productImages = listOf(
                            ProductImages(
                                "Red",
                                listOf(
                                    R.drawable.lays_red_front,
                                    R.drawable.lays_red_back
                                )
                            )
                        )
                    )
                )
            )
        )
    }
}