package com.piyush.domain.usecase

import com.piyush.domain.repository.CartRepository

class CartSummaryUseCase (private val repository: CartRepository){
    suspend fun execute(userId: Int) = repository.getCartSummary(userId)
}