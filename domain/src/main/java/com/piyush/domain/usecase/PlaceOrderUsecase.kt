package com.piyush.domain.usecase

import com.piyush.domain.model.AddressDomainModel
import com.piyush.domain.repository.OrderRepository

class PlaceOrderUsecase(val orderRepository: OrderRepository) {

    suspend fun execute(address: AddressDomainModel) = orderRepository.placeOrder(address)



}