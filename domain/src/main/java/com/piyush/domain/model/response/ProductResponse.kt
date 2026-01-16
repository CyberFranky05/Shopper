package com.piyush.domain.model.response

import com.piyush.domain.model.Product
import kotlinx.serialization.Serializable

@Serializable

data class ProductResponse(
    val data: List<Product>,
    val msg: String
)