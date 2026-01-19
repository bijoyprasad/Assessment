package com.bijoy.qbytezassessment.presentation.productdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bijoy.qbytezassessment.R
import com.bijoy.qbytezassessment.data.model.ModifierItem
import com.bijoy.qbytezassessment.data.model.ProductImages
import com.bijoy.qbytezassessment.data.model.ProductModel
import com.bijoy.qbytezassessment.data.model.ProductSku
import com.bijoy.qbytezassessment.data.model.ProductType
import com.bijoy.qbytezassessment.data.model.Storage
import com.bijoy.qbytezassessment.databinding.FragmentCartBinding
import com.bijoy.qbytezassessment.databinding.FragmentProductDetailsBinding
import com.bijoy.qbytezassessment.domain.navigation.AppNavigation
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.collections.filter
import kotlin.collections.firstOrNull
import kotlin.collections.isNotEmpty
import kotlin.getValue

class ProductDetailsFragment : Fragment(R.layout.fragment_product_details) {

    private lateinit var binding: FragmentProductDetailsBinding
    private val navigation: AppNavigation by inject()
    private val viewModel: ProductViewModel by viewModel()

    private lateinit var modelAdapter: ModelAdapter
    private lateinit var colorAdapter: ColorAdapter
    private lateinit var storageAdapter: StorageAdapter
    private lateinit var modifierAdapter: ModifierAdapter

    private var productSkus: List<ProductModel> = emptyList()
    private var productPrice = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        setupModifierSpinner()
        setupModifierRecycler()
        val productId = arguments?.getInt("product_id")
        productId?.let {
            viewModel.productId = it
            viewModel.loadProduct()
        }

        lifecycleScope.launch {
            viewModel.productFlow.collect {
                it?.let { product ->
                    binding.title.text = product.name
                    binding.price.price = product.price

                    viewModel.productSkuFlow.collect { skus ->
                        skus?.let {
                            productSkus = skus.models
                            if (productSkus.isNotEmpty())
                                loadProductSku(product.productType)
                        }
                    }
                }
            }
        }

        binding.btnAddToCart.setOnClickListener {
            lifecycleScope.launch {
                viewModel.selectedModifiers = modifierAdapter.getSelectedModifiers()
                viewModel.addItemToCart()
            }
        }
    }

    private fun setupRecyclerViews() {
        modelAdapter = ModelAdapter { selectedModel ->
            onModelSelected(selectedModel)
        }
        binding.rvModels.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = modelAdapter
        }

        colorAdapter = ColorAdapter { setupImageSlider(it.imageList) }
        binding.rvColors.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = colorAdapter
        }

        storageAdapter = StorageAdapter { selectedStorage ->
            onStorageSelected(selectedStorage)
        }
        binding.rvStorage.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = storageAdapter
        }
    }

    fun loadProductSku(type: ProductType) {
        when (type) {
            ProductType.MOBILE -> {
                setupImageSlider(
                    productSkus.firstOrNull()?.productImages?.firstOrNull()?.imageList
                        ?: emptyList()
                )
                modelAdapter.submitList(productSkus)
                modelAdapter.setSelected(0)
                onModelSelected(productSkus[0])
            }

            ProductType.GROCERY -> {
                setupImageSlider(
                    productSkus.firstOrNull()?.productImages?.firstOrNull()?.imageList
                        ?: emptyList()
                )
            }
        }
        reDesignView(type)
    }

    private fun onModelSelected(model: ProductModel) {
        val modelSkus = productSkus.filter { it.skuId == model.skuId }

        val colors = modelSkus.flatMap { it.productImages }.distinct()
        colorAdapter.submitList(colors)

        val storages = modelSkus.flatMap { it.storage ?: emptyList() }.distinct()
        storageAdapter.submitList(storages)

        // Auto-select defaults
        setupImageSlider(model.productImages[0].imageList)
        if (colors.isNotEmpty()) colorAdapter.setSelected(0)
        if (storages.isNotEmpty()) storageAdapter.setSelected(0)

        updatePricing(model.price, 0.0)
    }

    private fun onStorageSelected(storage: Storage) {
        val model = modelAdapter.getSelectedItem() ?: return
        val finalSku = productSkus.firstOrNull {
            it.name == model.name && it.storage?.contains(storage) == true
        } ?: return
        val selectedStorage = finalSku.storage
            ?.firstOrNull { it.name == storage.name }
            ?: return
        val basePrice = finalSku.price
        val storagePrice = selectedStorage.additionalPrice

        // 4️⃣ Update UI price
        updatePricing(basePrice, storagePrice)
    }

    fun updatePricing(price: Double, additionalPrice: Double) {
        val total = price + additionalPrice
        viewModel.totalPrice = total
        binding.price.price = total
    }

    fun setupImageSlider(imageList: List<Int>) {
        val pagerAdapter = ImageSliderAdapter(imageList)
        binding.viewPager.adapter = pagerAdapter

        val thumbAdapter = ThumbnailAdapter(imageList) { binding.viewPager.currentItem = it }

        binding.rvThumbnails.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvThumbnails.adapter = thumbAdapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                thumbAdapter.setSelected(position)
                binding.rvThumbnails.smoothScrollToPosition(position)
            }
        })
    }

    fun reDesignView(type: ProductType) {
        binding.tvChooseColor.isVisible = type == ProductType.MOBILE
        binding.rvColors.isVisible = type == ProductType.MOBILE
        binding.tvChooseModel.isVisible = type == ProductType.MOBILE
        binding.rvModels.isVisible = type == ProductType.MOBILE
        binding.tvChooseStorage.isVisible = type == ProductType.MOBILE
        binding.rvStorage.isVisible = type == ProductType.MOBILE
        binding.spModifiers.isVisible = type == ProductType.MOBILE
        binding.rvModifiers.isVisible = type == ProductType.MOBILE
    }

    private fun setupModifierRecycler() {
        modifierAdapter = ModifierAdapter()

        binding.rvModifiers.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = modifierAdapter
        }
    }

    private fun setupModifierSpinner() {
        val options = listOf("Modifier", "Custom Modifier")

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            options
        )

        binding.spModifiers.adapter = adapter

        binding.spModifiers.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    modifierAdapter.submitList(
                        when (position) {
                            0 -> viewModel.getModifierList()
                            1 -> viewModel.getCustomModifierList()
                            else -> emptyList()
                        }
                    )
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
    }

}