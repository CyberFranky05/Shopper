package com.piyush.data.model.response

import kotlinx.serialization.Serializable


@Serializable
data class PlaceOrderResponse(
    val `data`: OrderId,
    val msg : String
)