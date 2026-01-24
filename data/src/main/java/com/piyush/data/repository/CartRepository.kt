package com.piyush.data.repository

import com.piyush.domain.model.CartItemModel
import com.piyush.domain.model.CartModel
import com.piyush.domain.model.CartSummary
import com.piyush.domain.model.request.AddCartRequestModel
import com.piyush.domain.network.NetworkService
import com.piyush.domain.network.ResultWrapper
import com.piyush.domain.repository.CartRepository


class CartRepositoryImpl(val networkService: NetworkService): CartRepository {
    override suspend fun addProductToCart(request: AddCartRequestModel , userId: Long): ResultWrapper<CartModel> {
        return networkService.addProductToCart(request , userId)
    }

    override suspend fun getCart(userId: Long): ResultWrapper<CartModel> {
        return networkService.getCart(userId)
    }

    override suspend fun updateQuantity(cartItemModel: CartItemModel,userId: Long): ResultWrapper<CartModel> {
        return networkService.updateQuantity(cartItemModel , userId)
    }

    override suspend fun deleteProduct(
        cartItemId: Int,
        userId: Long
    ): ResultWrapper<CartModel> {
        return networkService.deleteProduct(cartItemId, userId)
    }

    override suspend fun getCartSummary(userId: Long): ResultWrapper<CartSummary> {
        return networkService.getCartSummary(userId)
    }


}
