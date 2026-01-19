package com.piyush.shopper.Navigation

import com.piyush.domain.model.Product
import com.piyush.shopper.model.UiProductModel
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
object CartScreen

@Serializable
object ProfileScreen

@Serializable
data class ProductDetails(val product: UiProductModel)