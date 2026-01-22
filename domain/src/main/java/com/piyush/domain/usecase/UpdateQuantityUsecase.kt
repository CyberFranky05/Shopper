package com.piyush.domain.usecase

import com.piyush.domain.model.CartItemModel
import com.piyush.domain.repository.CartRepository

class UpdateQuantityUsecase (private val cartRepository: CartRepository){
    suspend fun execute(cartItemModel: CartItemModel) = cartRepository.updateQuantity(cartItemModel)
}


