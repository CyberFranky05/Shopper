package com.piyush.shopper.di


import com.piyush.shopper.ui.feature.Profile.ProfileViewModel
import com.piyush.shopper.ui.feature.Summary.CartSummaryViewModel
import com.piyush.shopper.ui.feature.account.login.LoginViewModel
import com.piyush.shopper.ui.feature.account.register.RegisterViewModel
import com.piyush.shopper.ui.feature.cart.CartViewModel
import com.piyush.shopper.ui.feature.home.HomeViewModel
import com.piyush.shopper.ui.feature.orders.OrdersViewModel
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
        ProductDetailsViewModel(
            get(),
            get()
        )
    }

    viewModel {
        CartViewModel(
            get(),
            get(),
            get(),
            get()
        )
    }

    viewModel {
        CartSummaryViewModel(
            get(),
            get(),
            get()
        )
    }

    viewModel {
        OrdersViewModel(get(),
            get()
        )
    }

    viewModel {
        LoginViewModel(
            get(),
            get()
        )
    }

    viewModel {
        RegisterViewModel(
            get(),
            get()
        )
    }

    viewModel {
        ProfileViewModel(
            get()
        )

    }

}