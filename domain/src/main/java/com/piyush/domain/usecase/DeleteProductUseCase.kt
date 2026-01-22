package com.piyush.domain.usecase

import com.piyush.domain.repository.CartRepository

class DeleteProductUseCase (private val cartRepository: CartRepository){
    suspend fun execute(cartItemId: Int , userId: Int) = cartRepository.deleteProduct(cartItemId, userId)
}
