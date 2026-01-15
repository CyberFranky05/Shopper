package com.piyush.domain.usecase

import com.piyush.domain.repository.CategoryRepository

class GetCategoryUsecase(private val repository: CategoryRepository) {
    suspend fun execute() = repository.getCategories()
}