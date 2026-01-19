package com.bijoy.qbytezassessment.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 1,

    val productId: Int,

    val name: String,

    val price: Double,

    val quantity: Int,

    var productType: String,

    var imagePath: Int,

    val modifierIds: List<Int>
)