package com.bijoy.qbytezassessment.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bijoy.qbytezassessment.data.model.CartItem
import com.bijoy.qbytezassessment.data.model.ProductSku
import com.bijoy.qbytezassessment.domain.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartRepository: CartRepository
): ViewModel() {

    private val cartStateFlow = MutableStateFlow<List<CartItem>>(emptyList())
    internal val cartFlow: StateFlow<List<CartItem>> = cartStateFlow.asStateFlow()

    init {
        fetchCart()
    }

    fun fetchCart() {
        viewModelScope.launch {
            val result = cartRepository.getCartItems()
            cartStateFlow.emit(result)
        }
    }
}