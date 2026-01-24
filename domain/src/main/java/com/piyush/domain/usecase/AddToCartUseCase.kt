package com.piyush.domain.usecase

import com.piyush.domain.model.request.AddCartRequestModel
import com.piyush.domain.repository.CartRepository

class AddToCartUseCase(private val cartRepository: CartRepository ) {
    suspend fun execute(request: AddCartRequestModel,userId: Long) = cartRepository.addProductToCart(request, userId)
}