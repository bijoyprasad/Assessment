package com.bijoy.qbytezassessment.data.model

data class ProductSku(
    var id: Int,

    var productId: Int,

    var skuIds: List<Int>,

    var models: List<ProductModel>
)

data class ProductModel(
    var skuId: Int,

    var name: String? = null,

    var display: String? = null,

    var price: Double = 0.0,

    var productImages: List<ProductImages>,

    var storage: List<Storage>? = null
)

data class Storage(
    var name: String,

    var additionalPrice: Double,
)
