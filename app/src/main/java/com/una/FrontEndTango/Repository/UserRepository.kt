package com.una.FrontEndTango.Repository

import com.una.FrontEndTango.Model.UserRequest
import com.una.FrontEndTango.Service.UserService

class UserRepository constructor(
    private val userService: UserService
){
    suspend fun getAllUser() = userService.getAllUsers()

    suspend fun getUserById(id : Long) = userService.getUserById(id)

    suspend fun deleteUserById(id : Long) = userService.deleteUserById(id)

    suspend fun createUser(userRequest: UserRequest) = userService.createUser(userRequest)

    suspend fun updateUser(userRequest: UserRequest) = userService.updateUser(userRequest)


}
