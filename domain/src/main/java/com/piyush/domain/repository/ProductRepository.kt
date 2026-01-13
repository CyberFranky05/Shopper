package com.piyush.domain.repository

import com.piyush.domain.model.Product
import com.piyush.domain.network.ResultWrapper

interface ProductRepository {
    suspend fun getProducts(): ResultWrapper<List<Product>>
}