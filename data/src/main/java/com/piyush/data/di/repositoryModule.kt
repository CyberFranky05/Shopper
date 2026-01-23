package com.piyush.data.di

import com.piyush.data.repository.CartRepositoryImpl
import com.piyush.data.repository.CategoryRepositoryImpl
import com.piyush.data.repository.OrderRepositoryImpl
import com.piyush.data.repository.ProductRepositoryImpl
import com.piyush.domain.repository.CartRepository
import com.piyush.domain.repository.CategoryRepository
import com.piyush.domain.repository.OrderRepository
import com.piyush.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository> {
        ProductRepositoryImpl(get())
    }
    single<CategoryRepository>{
        CategoryRepositoryImpl(get())
    }

    single<CartRepository>{
        CartRepositoryImpl(get())

    }

    single<OrderRepository> {
        OrderRepositoryImpl(get())
    }
}