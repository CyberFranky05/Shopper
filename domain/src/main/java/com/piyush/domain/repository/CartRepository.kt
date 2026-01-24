package com.piyush.domain.repository

import com.piyush.domain.model.CartItemModel
import com.piyush.domain.model.CartModel
import com.piyush.domain.model.CartSummary
import com.piyush.domain.model.request.AddCartRequestModel
import com.piyush.domain.network.ResultWrapper

interface CartRepository {
    suspend fun addProductToCart(
        request: AddCartRequestModel,
        userId: Long
    ): ResultWrapper<CartModel>


    suspend fun getCart(userId: Long): ResultWrapper<CartModel>
    suspend fun updateQuantity(cartItemModel: CartItemModel,userId: Long): ResultWrapper<CartModel>
    suspend fun deleteProduct(cartItemId: Int, userId: Long): ResultWrapper<CartModel>

    suspend fun getCartSummary(userId: Long): ResultWrapper<CartSummary>
}



