package com.bijoy.qbytezassessment.data.model

data class CartItem(
    val id: Int,

    val productId: Int,

    val name: String,

    var price: Double,

    var quantity: Int,

    var productType: ProductType,

    var imagePath: Int,

    val modifierItems: List<ModifierItem>
)