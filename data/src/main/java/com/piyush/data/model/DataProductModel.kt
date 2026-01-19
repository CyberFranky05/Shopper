package com.piyush.data.model

import com.piyush.domain.model.Product
import kotlinx.serialization.Serializable


@Serializable
data class DataProductModel (
    val id : Int,
    val title: String,
    val price: Double,
    val categoryId: Int?,
    val description: String,
    val image: String
    ){
    fun toProduct() = Product(
        id = id ,
        title = title,
        price = price,
        categoryId= categoryId,
        description= description,
        image = image

    )
}