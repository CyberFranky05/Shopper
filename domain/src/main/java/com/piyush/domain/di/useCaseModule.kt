package com.piyush.domain.di

import androidx.lifecycle.ViewModelProvider
import com.piyush.domain.usecase.AddToCartUseCase
import com.piyush.domain.usecase.CartSummaryUseCase
import com.piyush.domain.usecase.DeleteProductUseCase
import com.piyush.domain.usecase.GetCartUseCase
import com.piyush.domain.usecase.GetCategoryUsecase
import com.piyush.domain.usecase.GetProductUseCase
import com.piyush.domain.usecase.LoginUsecase
import com.piyush.domain.usecase.OrderListUseCase
import com.piyush.domain.usecase.PlaceOrderUsecase
import com.piyush.domain.usecase.RegisterUseCase
import com.piyush.domain.usecase.UpdateQuantityUsecase
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetProductUseCase(get()) }
    factory { GetCategoryUsecase(get ()) }
    factory { AddToCartUseCase(get()) }
    factory { GetCartUseCase(get ()) }
    factory { UpdateQuantityUsecase(get ()) }
    factory { DeleteProductUseCase(get()) }
    factory { CartSummaryUseCase(get ()) }
    factory { PlaceOrderUsecase(get()) }
    factory { OrderListUseCase(get()) }
    factory { LoginUsecase(get()) }
    factory { RegisterUseCase(get()) }

}