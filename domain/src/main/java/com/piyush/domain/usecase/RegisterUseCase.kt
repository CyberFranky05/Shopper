package com.piyush.domain.usecase

import com.piyush.domain.repository.UserRepository

class RegisterUseCase (private val userRegisterUseCase: UserRepository){
    suspend fun execute(email: String, password: String, name: String) = userRegisterUseCase.register(email, password, name)
}
