package com.bijoy.qbytezassessment.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val name: String,

    val price: Double,

    var imagePath: Int,

    var productType: String
)