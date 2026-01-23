package com.piyush.domain.network

import com.piyush.domain.model.AddressDomainModel
import com.piyush.domain.model.CartItemModel
import com.piyush.domain.model.CartModel
import com.piyush.domain.model.CartSummary
import com.piyush.domain.model.OrdersListModel
import com.piyush.domain.model.request.AddCartRequestModel
import com.piyush.domain.model.response.CategoryResponse
import com.piyush.domain.model.response.ProductResponse

interface NetworkService{
    suspend fun getProducts(category: Int?): ResultWrapper<ProductResponse>

    suspend fun getCategories(): ResultWrapper<CategoryResponse>


    suspend fun addProductToCart(
        request: AddCartRequestModel
    ): ResultWrapper<CartModel>
    suspend fun getCart(): ResultWrapper<CartModel>
    suspend fun updateQuantity(cartItemModel: CartItemModel): ResultWrapper<CartModel>
    suspend fun deleteProduct(cartItemId: Int, userId: Int): ResultWrapper<CartModel>
    suspend fun getCartSummary(userId: Int) : ResultWrapper<CartSummary>
    suspend fun placeOrder(address: AddressDomainModel , userId: Int): ResultWrapper<Long>
    suspend fun getOrderList(): ResultWrapper<OrdersListModel>


}


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class Failure(val exception: Exception): ResultWrapper<Nothing>()
}