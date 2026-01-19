package com.bijoy.qbytezassessment.data.repository.convertor

import com.bijoy.qbytezassessment.data.database.CartEntity
import com.bijoy.qbytezassessment.data.database.ModifierEntity
import com.bijoy.qbytezassessment.data.database.ProductEntity
import com.bijoy.qbytezassessment.data.model.CartItem
import com.bijoy.qbytezassessment.data.model.ModifierItem
import com.bijoy.qbytezassessment.data.model.Product
import com.bijoy.qbytezassessment.data.model.ProductType


fun ProductEntity.toDomain(): Product {
    return Product(
        id = this.id,
        name = this.name,
        imagePath = this.imagePath,
        price = this.price,
        productType = ProductType.valueOf(this.productType)
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = this.id,
        name = this.name,
        price = this.price,
        imagePath = this.imagePath,
        productType = this.productType.name
    )
}

fun CartEntity.toDomain(
    allModifiers: List<ModifierItem>
): CartItem {
    val mappedModifiers = allModifiers.filter { modifierIds.contains(it.id) }

    return CartItem(
        id = id,
        productId = productId,
        name = name,
        price = price,
        quantity = quantity,
        productType = ProductType.valueOf(this.productType),
        imagePath = this.imagePath,
        modifierItems = mappedModifiers
    )
}

fun CartItem.toEntity() = CartEntity(
    id = id,
    productId = productId,
    name = name,
    price = price,
    quantity = quantity,
    productType = this.productType.name,
    imagePath = this.imagePath,
    modifierIds = modifierItems.map { it.id })

fun ModifierEntity.toDomain() = ModifierItem(
    id = id, productId = productId, name = name, price = price, quantity = quantity
)

fun ModifierItem.toEntity() = ModifierEntity(
    id = id, productId = productId, name = name, price = price, quantity = quantity
)