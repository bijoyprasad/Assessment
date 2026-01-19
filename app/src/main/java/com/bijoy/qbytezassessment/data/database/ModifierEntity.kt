package com.bijoy.qbytezassessment.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "modifier_items",
    primaryKeys = ["id", "productId"]
)
data class ModifierEntity(
    val id: Int,

    val productId: Int,

    val name: String,

    val price: Double,

    val quantity: Int
)