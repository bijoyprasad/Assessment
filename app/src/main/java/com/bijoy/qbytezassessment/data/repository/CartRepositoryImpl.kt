package com.bijoy.qbytezassessment.data.repository

import com.bijoy.qbytezassessment.data.database.CartDao
import com.bijoy.qbytezassessment.data.database.ModifierEntity
import com.bijoy.qbytezassessment.data.model.CartItem
import com.bijoy.qbytezassessment.data.repository.convertor.toDomain
import com.bijoy.qbytezassessment.data.repository.convertor.toEntity
import com.bijoy.qbytezassessment.domain.repository.CartRepository

class CartRepositoryImpl(
    private val cartDao: CartDao
): CartRepository {

    override suspend fun addToCart(item: CartItem) {
        //cartDao.clearCart()
        //cartDao.clearModifier()

        cartDao.insertCartItem(item.toEntity())
        if (item.modifierItems.isNotEmpty()) {
            val modifierEntities = item.modifierItems.map { modifier ->
                ModifierEntity(
                    id = modifier.id,
                    productId = item.productId,
                    quantity = modifier.quantity,
                    name = modifier.name,
                    price = modifier.price,
                )
            }
            cartDao.insertModifiers(modifierEntities)
        }
    }

    override suspend fun getCartItems(): List<CartItem> {
        val cartEntities = cartDao.getCartItems()
        val modifierEntities = cartDao.getAllModifiers()

        val modifierItems = modifierEntities.map { it.toDomain() }

        return cartEntities.map { cart ->
            cart.toDomain(modifierItems)
        }
    }

    override suspend fun removeCartItem(id: Int) {
        cartDao.deleteCartItem(id)
    }

    override suspend fun isProductInCart(productId: Int): Boolean {
        return cartDao.isProductInCart(productId)
    }
}