package com.piyush.data.repository

import com.piyush.domain.model.response.CategoryResponse
import com.piyush.domain.network.NetworkService
import com.piyush.domain.network.ResultWrapper
import com.piyush.domain.repository.CategoryRepository

class CategoryRepositoryImpl(val networkInterface : NetworkService) : CategoryRepository{
    override suspend fun getCategories(): ResultWrapper<CategoryResponse> {
        return networkInterface.getCategories()
    }
}