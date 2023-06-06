package com.una.FrontEndTango.Service

import com.una.FrontEndTango.Model.Priority
import retrofit2.Response
import retrofit2.http.GET

interface PriorityService {

    @GET("v1/priorities")
    suspend fun getAllPriorities(): Response<List<Priority>>

    companion object {
        private var service: PriorityService? = null
        fun getInstance(): PriorityService {
            if (service == null) {
                service = ServiceBuilder.buildService(PriorityService::class.java)
            }
            return service!!
        }
    }
}