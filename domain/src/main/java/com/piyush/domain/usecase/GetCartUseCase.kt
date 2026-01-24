package com.piyush.domain.usecase

import com.piyush.domain.repository.CartRepository

class GetCartUseCase(val cartRepository: CartRepository){
    suspend fun execute(userId: Long) = cartRepository.getCart(userId)

}