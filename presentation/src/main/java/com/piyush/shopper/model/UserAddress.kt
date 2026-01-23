package com.piyush.shopper.model

import android.os.Parcelable
import com.piyush.data.model.request.AddressDataModel
import com.piyush.domain.model.AddressDomainModel
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class UserAddress(
    val addressLine: String,
    val city: String,
    val state: String,
    val postalCode: String,
    val country: String
): Parcelable {
    override fun toString(): String {
        return "$addressLine, $city, $state, $postalCode, $country"

    }

    fun toAddressDataModel() = AddressDomainModel(
        addressLine = addressLine,
        city = city,
        state = state,
        postalCode = postalCode,
        country = country
    )

}