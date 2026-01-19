package com.bijoy.qbytezassessment.data.di

import androidx.navigation.NavController
import com.bijoy.qbytezassessment.data.database.AppDatabase
import com.bijoy.qbytezassessment.data.navigation.AppNavigationImpl
import com.bijoy.qbytezassessment.data.repository.CartRepositoryImpl
import com.bijoy.qbytezassessment.data.repository.DashboardRepositoryImpl
import com.bijoy.qbytezassessment.data.repository.ProductRepositoryImpl
import com.bijoy.qbytezassessment.domain.navigation.AppNavigation
import com.bijoy.qbytezassessment.domain.repository.CartRepository
import com.bijoy.qbytezassessment.domain.repository.DashboardRepository
import com.bijoy.qbytezassessment.domain.repository.ProductRepository
import com.bijoy.qbytezassessment.presentation.cart.CartViewModel
import com.bijoy.qbytezassessment.presentation.dashboard.DashboardViewModel
import com.bijoy.qbytezassessment.presentation.productdetails.ProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getInstance(androidContext()) }

    single { get<AppDatabase>().productDao() }
    single { get<AppDatabase>().cartDao() }

    single<DashboardRepository> { DashboardRepositoryImpl(get()) }
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    single<CartRepository> { CartRepositoryImpl(get()) }
}

val navModule = module {
    factory<AppNavigation> { AppNavigationImpl() }
}

val viewModelModule = module {
    viewModelOf(::DashboardViewModel)
    viewModelOf(::ProductViewModel)
    viewModelOf(::CartViewModel)
}