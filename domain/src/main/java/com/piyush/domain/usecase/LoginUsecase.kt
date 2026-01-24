package com.piyush.domain.usecase

import com.piyush.domain.repository.UserRepository

class  LoginUsecase(private val userRepository: UserRepository){
    suspend fun execute(username: String , password: String) = userRepository.login(username, password)
}