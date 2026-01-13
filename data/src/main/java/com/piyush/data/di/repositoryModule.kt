package com.piyush.data.di

import com.piyush.data.repository.ProductRepositoryImpl
import com.piyush.domain.repository.ProductRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepository> {
        ProductRepositoryImpl(get())
    }
}