package com.bijoy.qbytezassessment.domain.repository

import android.util.Log
import com.bijoy.qbytezassessment.data.model.CartItem

interface CartRepository {

    suspend fun addToCart(item: CartItem)

    suspend fun getCartItems(): List<CartItem>

    suspend fun removeCartItem(id: Int)

    suspend fun isProductInCart(productId: Int): Boolean

    fun load() {
        Log.e("OKK", "TEST")
    }

}