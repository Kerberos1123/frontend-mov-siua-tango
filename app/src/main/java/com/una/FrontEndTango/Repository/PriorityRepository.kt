package com.una.FrontEndTango.Repository

import com.una.FrontEndTango.Service.PriorityService

class PriorityRepository constructor(
    private val priorityService: PriorityService
){
    suspend fun getAllPriorities() = priorityService.getAllPriorities()
}