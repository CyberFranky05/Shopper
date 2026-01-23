package com.piyush.shopper.Navigation

import com.piyush.domain.model.Product
import com.piyush.shopper.model.UiProductModel
import com.piyush.shopper.model.UserAddress
import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
object CartScreen

@Serializable
object OrdersScreen

@Serializable
object ProfileScreen

@Serializable
object CartSummaryScreen

@Serializable
data class ProductDetails(val product: UiProductModel)

@Serializable
data class UserAddressRoute(val userAddressWrapper: UserAddressRouteWrapper)