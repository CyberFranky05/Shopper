package com.piyush.domain.repository

import com.piyush.domain.model.Product
import com.piyush.domain.model.response.ProductResponse
import com.piyush.domain.network.ResultWrapper

interface ProductRepository {
    suspend fun getProducts(category:Int?): ResultWrapper<ProductResponse>
}