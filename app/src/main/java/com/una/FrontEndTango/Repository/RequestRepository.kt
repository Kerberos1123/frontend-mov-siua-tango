package com.una.FrontEndTango.Repository

import com.una.FrontEndTango.Model.RequestRequest
import  com.una.FrontEndTango.Service.RequestService

class RequestRepository constructor(
    private val requestService: RequestService
){
    suspend fun getAllRequest() = requestService.getAllRequest()

    suspend fun getRequestById(id : Long) = requestService.getRequestById(id)

    suspend fun deleteRequestById(id : Long) = requestService.deleteRequestById(id)

    suspend fun createRequest(requestRequest: RequestRequest) = requestService.createRequest(requestRequest)

    suspend fun updateRequest(requestRequest: RequestRequest) = requestService.updateRequest(requestRequest)

}