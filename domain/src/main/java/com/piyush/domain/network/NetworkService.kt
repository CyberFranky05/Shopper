package com.piyush.domain.network

import com.piyush.domain.model.CartModel
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



}


sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class Failure(val exception: Exception): ResultWrapper<Nothing>()
}