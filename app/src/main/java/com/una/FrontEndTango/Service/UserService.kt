package com.una.FrontEndTango.Service

import com.una.FrontEndTango.Model.UserRequest
import com.una.FrontEndTango.Model.UserResponse

import retrofit2.Response
import retrofit2.http.*


interface UserService {

    @GET("v1/users")
    suspend fun getAllUsers() : Response<List<UserResponse>>

    @GET("v1/users/{id}")
    suspend fun getUserById(@Path("id") id: Long) : Response<UserResponse>

    @DELETE("v1/users/{id}")
    suspend fun deleteUserById(@Path("id") id: Long): Response<Void>

    @POST("v1/users")
    suspend fun createUser(@Body taskRequest: UserRequest) : Response<UserResponse>

    @PUT("v1/users")
    suspend fun updateUser(@Body taskRequest: UserRequest) : Response<UserResponse>

    companion object{
        private var userService : UserService? = null
        fun getInstance() : UserService {
            if (userService == null) {
                userService = ServiceBuilder.buildService(UserService::class.java)
            }
            return userService!!
        }
    }
}
