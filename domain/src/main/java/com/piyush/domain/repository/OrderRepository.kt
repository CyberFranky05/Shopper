package com.piyush.domain.repository

import com.piyush.domain.model.AddressDomainModel
import com.piyush.domain.model.OrdersListModel
import com.piyush.domain.network.ResultWrapper

interface OrderRepository {
    suspend fun placeOrder(addressDomainModel: AddressDomainModel): ResultWrapper<Long>
    suspend fun getOrderList(userId: Long): ResultWrapper<OrdersListModel>
}