package com.piyush.data.repository

import com.piyush.domain.model.Product
import com.piyush.domain.model.response.ProductResponse
import com.piyush.domain.network.NetworkService
import com.piyush.domain.network.ResultWrapper
import com.piyush.domain.repository.ProductRepository

class ProductRepositoryImpl (val networkService: NetworkService): ProductRepository{
    override suspend fun getProducts(category:Int?): ResultWrapper<ProductResponse> {
        return networkService.getProducts(category)
    }

}