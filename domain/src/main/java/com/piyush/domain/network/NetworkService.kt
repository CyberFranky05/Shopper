package com.piyush.domain.network

import com.piyush.domain.model.AddressDomainModel
import com.piyush.domain.model.CartItemModel
import com.piyush.domain.model.CartModel
import com.piyush.domain.model.CartSummary
import com.piyush.domain.model.OrdersListModel
import com.piyush.domain.model.UserDomainModel
import com.piyush.domain.model.request.AddCartRequestModel
import com.piyush.domain.model.response.CategoryResponse
import com.piyush.domain.model.response.ProductResponse

interface NetworkService{
    suspend fun getProducts(category: Int?): ResultWrapper<ProductResponse>

    suspend fun getCategories(): ResultWrapper<CategoryResponse>


    suspend fun addProductToCart(
        request: AddCartRequestModel,
        userId: Long
    ): ResultWrapper<CartModel>
    suspend fun getCart(userId:Long): ResultWrapper<CartModel>
    suspend fun updateQuantity(cartItemModel: CartItemModel , userId: Long): ResultWrapper<CartModel>
    suspend fun deleteProduct(cartItemId: Int, userId: Long): ResultWrapper<CartModel>
    suspend fun getCartSummary(userId: Long) : ResultWrapper<CartSummary>
    suspend fun placeOrder(address: AddressDomainModel , userId: Long): ResultWrapper<Long>
    suspend fun getOrderList(userId: Long): ResultWrapper<OrdersListModel>
    suspend fun login(email: String, password: String): ResultWrapper<UserDomainModel>
    suspend fun register(
        email: String,
        password: String,
        name: String
    ): ResultWrapper<UserDomainModel>


}


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class Failure(val exception: Exception): ResultWrapper<Nothing>()
}