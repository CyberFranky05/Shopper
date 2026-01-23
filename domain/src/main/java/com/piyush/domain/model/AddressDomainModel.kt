package com.piyush.domain.model

import kotlinx.serialization.Serializable


data class AddressDomainModel(
    val addressLine: String,
    val city: String,
    val state: String,
    val postalCode: String,
    val country: String

)