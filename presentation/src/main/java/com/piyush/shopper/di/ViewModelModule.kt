package com.piyush.shopper.di


import com.piyush.shopper.ui.feature.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        HomeViewModel(
            get()
        )
    }
}