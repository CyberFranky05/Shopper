package com.piyush.domain.model

import io.ktor.http.Url

data class CartItemModel (
    val id: Int,
    val productId: Int,
    val userId: Int,
    val name: String,
    val price: Double,
    val imageUrl : String?,
    val quantity: Int,
    val productName: String
)