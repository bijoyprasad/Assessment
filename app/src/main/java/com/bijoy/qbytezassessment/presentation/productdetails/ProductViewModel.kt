package com.bijoy.qbytezassessment.presentation.productdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bijoy.qbytezassessment.data.model.CartItem
import com.bijoy.qbytezassessment.data.model.ModifierItem
import com.bijoy.qbytezassessment.data.model.Product
import com.bijoy.qbytezassessment.data.model.ProductSku
import com.bijoy.qbytezassessment.data.model.ProductType
import com.bijoy.qbytezassessment.domain.repository.CartRepository
import com.bijoy.qbytezassessment.domain.repository.DashboardRepository
import com.bijoy.qbytezassessment.domain.repository.ProductRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    internal var productId: Int? = null
    internal var product: Product? = null
    internal var totalPrice: Double = 0.0
    internal var selectedModifiers: List<ModifierItem> = emptyList()

    private val productStateFlow = MutableStateFlow<Product?>(null)
    internal val productFlow: StateFlow<Product?> = productStateFlow.asStateFlow()

    private val productSkuStateFlow = MutableStateFlow<ProductSku?>(null)
    internal val productSkuFlow: StateFlow<ProductSku?> = productSkuStateFlow.asStateFlow()

    fun loadProduct() {
        productId?.let {
            viewModelScope.launch {
                val result = productRepository.getProduct(it)
                productStateFlow.emit(result)
                product = result
                totalPrice = result.price

                val skuResult = productRepository.getProductSku(it)
                productSkuStateFlow.emit(skuResult)
            }
        }
    }

    fun addItemToCart() {
        viewModelScope.launch {
            product?.let { product ->
                cartRepository.addToCart(
                    CartItem(
                        id = getCartId(),
                        productId = productId ?: -1,
                        name = product.name,
                        price = totalPrice,
                        quantity = 1,
                        productType = product.productType,
                        imagePath = product.imagePath,
                        modifierItems =
                            if (product.productType == ProductType.MOBILE)
                                selectedModifiers
                            else
                                emptyList()
                    )
                )
            }

        }
    }

    fun getModifierList() = listOf(
        ModifierItem(
            1,
            productId ?: -1,
            "Mobile Cover",
            20.0,
            1
        ),
        ModifierItem(
            2,
            productId ?: -1,
            "Screen Cover",
            20.0,
            1
        )
    )

    fun getCustomModifierList() = listOf(
        ModifierItem(
            3,
            productId ?: -1,
            "Charger",
            50.0,
            1
        ),
        ModifierItem(
            4,
            productId ?: -1,
            "Back Case",
            15.0,
            1
        )
    )

    fun getCartId(): Int {
        return (productId?: -1 ) * 100
    }

}