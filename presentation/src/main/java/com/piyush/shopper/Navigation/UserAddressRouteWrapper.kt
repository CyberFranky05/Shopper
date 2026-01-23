package com.piyush.shopper.Navigation

import android.os.Parcelable
import com.piyush.shopper.model.UserAddress
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class UserAddressRouteWrapper(
    val userAddress: UserAddress?
) : Parcelable