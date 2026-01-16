package com.piyush.domain.model.response

import com.piyush.domain.model.CategoryModel
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse (
    val data: List<CategoryModel>,
    val msg: String
)