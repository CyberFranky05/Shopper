package com.piyush.domain.repository

import com.piyush.domain.model.CartModel
import com.piyush.domain.model.request.AddCartRequestModel
import com.piyush.domain.network.ResultWrapper

interface CartRepository {
    suspend fun addProductToCart(
        request: AddCartRequestModel
    ): ResultWrapper<CartModel>


    suspend fun getCart(): ResultWrapper<CartModel>


}