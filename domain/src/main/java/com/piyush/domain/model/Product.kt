package com.piyush.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Product(
    val id : Long,
    val title: String,
    val price: Double,
    val categoryId: Int?,
    val description: String,
    val image: String
){
    val priceString : String
        get()  = "$$price"
}
