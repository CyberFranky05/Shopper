package com.piyush.data.repository

import com.piyush.domain.model.CartModel
import com.piyush.domain.model.request.AddCartRequestModel
import com.piyush.domain.network.NetworkService
import com.piyush.domain.network.ResultWrapper
import com.piyush.domain.repository.CartRepository


class CartRepositoryImpl(val networkService: NetworkService): CartRepository {
    override suspend fun addProductToCart(request: AddCartRequestModel): ResultWrapper<CartModel> {
        return networkService.addProductToCart(request)
    }

    override suspend fun getCart(): ResultWrapper<CartModel> {
        return networkService.getCart()
    }


}
