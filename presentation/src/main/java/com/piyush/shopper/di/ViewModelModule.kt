package com.piyush.shopper.di


import com.piyush.shopper.ui.feature.home.HomeViewModel
import com.piyush.shopper.ui.feature.product_details.ProductDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        HomeViewModel(
            get(),
            get()
        )
    }

    viewModel {
        ProductDetailsViewModel()

    }
}