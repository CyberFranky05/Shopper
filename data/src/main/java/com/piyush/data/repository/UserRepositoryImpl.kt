package com.piyush.data.repository

import com.piyush.data.network.NetworkServiceImpl
import com.piyush.domain.model.UserDomainModel
import com.piyush.domain.network.NetworkService
import com.piyush.domain.network.ResultWrapper
import com.piyush.domain.repository.UserRepository

class UserRepositoryImpl (private val networkService: NetworkService): UserRepository{
    override suspend fun login(email: String, password: String) =
        networkService.login(email, password)


    override suspend fun register(
        email: String,
        password: String,
        name: String
    ) = networkService.register(email , password , name)

}
