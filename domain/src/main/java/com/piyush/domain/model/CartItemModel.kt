package com.piyush.domain.model

import io.ktor.http.Url

data class CartItemModel (
    val id: Int,
    val productId: Int,
    val price: Double,
    val imageUrl : String?=null,
    val quantity: Int,
    val productName: String
)