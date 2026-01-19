package com.bijoy.qbytezassessment.data.model

data class ModifierItem(
    val id: Int,

    val productId: Int,

    val name: String,

    var price: Double,

    var quantity: Int = 1
)