package com.piyush.domain.di

import com.piyush.domain.usecase.GetCategoryUsecase
import com.piyush.domain.usecase.GetProductUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductUseCase(get()) }
    factory { GetCategoryUsecase(get()) }
}