package com.piyush.data.model.response

import com.piyush.domain.model.OrdersListModel
import kotlinx.serialization.Serializable
import kotlin.collections.map

@Serializable
data class OrdersListResponse(
    val `data`: List<OrderListData>,
    val msg: String
) {
    fun toDomainResponse(): OrdersListModel {
        return OrdersListModel(
            `data` = `data`.map { it.toDomainResponse() },
            msg = msg
        )
    }
}