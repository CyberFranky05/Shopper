package com.piyush.domain.repository

import com.piyush.domain.model.CartItemModel
import com.piyush.domain.model.CartModel
import com.piyush.domain.model.CartSummary
import com.piyush.domain.model.request.AddCartRequestModel
import com.piyush.domain.network.ResultWrapper

interface CartRepository {
    suspend fun addProductToCart(
        request: AddCartRequestModel
    ): ResultWrapper<CartModel>


    suspend fun getCart(): ResultWrapper<CartModel>
    suspend fun updateQuantity(cartItemModel: CartItemModel): ResultWrapper<CartModel>
    suspend fun deleteProduct(cartItemId: Int, userId: Int): ResultWrapper<CartModel>

    suspend fun getCartSummary(userId: Int): ResultWrapper<CartSummary>
}



