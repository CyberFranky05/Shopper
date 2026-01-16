package com.piyush.domain.repository

import com.piyush.domain.model.response.CategoryResponse
import com.piyush.domain.network.ResultWrapper

interface CategoryRepository {
    suspend fun getCategories(): ResultWrapper<CategoryResponse>
}