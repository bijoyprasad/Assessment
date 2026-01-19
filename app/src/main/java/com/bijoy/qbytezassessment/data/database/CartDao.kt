package com.bijoy.qbytezassessment.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cart: CartEntity)

    @Query("SELECT * FROM cart_items")
    suspend fun getCartItems(): List<CartEntity>

    @Query("DELETE FROM cart_items WHERE productId = :id")
    suspend fun deleteCartItem(id: Int)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModifiers(modifiers: List<ModifierEntity>)

    @Query("SELECT * FROM modifier_items")
    suspend fun getAllModifiers(): List<ModifierEntity>

    @Query("DELETE FROM modifier_items WHERE id = :productId")
    suspend fun deleteModifiersByCart(productId: Int)

    @Query("DELETE FROM modifier_items")
    suspend fun clearModifier()

    @Query("SELECT EXISTS(SELECT 1 FROM cart_items WHERE productId = :productId)")
    suspend fun isProductInCart(productId: Int): Boolean
}