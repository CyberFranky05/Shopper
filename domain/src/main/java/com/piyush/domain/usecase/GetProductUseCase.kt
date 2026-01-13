package com.piyush.domain.usecase

import com.piyush.domain.repository.ProductRepository

class GetProductUseCase(private val repository: ProductRepository) {
    suspend fun execute() = repository.getProducts()
}