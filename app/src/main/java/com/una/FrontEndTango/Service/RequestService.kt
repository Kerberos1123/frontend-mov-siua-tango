package com.una.FrontEndTango.Service

import com.una.FrontEndTango.Model.RequestRequest
import com.una.FrontEndTango.Model.RequestResponse

import retrofit2.Response
import retrofit2.http.*


interface RequestService {

    @GET("v1/requests")
    suspend fun getAllRequest() : Response<List<RequestResponse>>

    @GET("v1/requests/{id}")
    suspend fun getRequestById(@Path("id") id: Long) : Response<RequestResponse>

    @DELETE("v1/requests/{id}")
    suspend fun deleteRequestById(@Path("id") id: Long): Response<Void>

    @POST("v1/requests")
    suspend fun createRequest(@Body taskRequest: RequestRequest) : Response<RequestResponse>

    @PUT("v1/requests")
    suspend fun updateRequest(@Body taskRequest: RequestRequest) : Response<RequestResponse>

    companion object{
        private var requestService : RequestService? = null
        fun getInstance() : RequestService {
            if (requestService == null) {
                requestService = ServiceBuilder.buildService(RequestService::class.java)
            }
            return requestService!!
        }
    }
}
