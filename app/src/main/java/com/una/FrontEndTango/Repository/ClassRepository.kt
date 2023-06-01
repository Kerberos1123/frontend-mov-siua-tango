package com.una.FrontEndTango.Repository

import com.una.FrontEndTango.Model.ClassRequest
import com.una.FrontEndTango.Service.ClassService

class ClassRepository constructor(
    private val classService: ClassService
){
    suspend fun getAllClass() = classService.getAllClasses()

    suspend fun getClassById(id : Long) = classService.getClassById(id)

    suspend fun deleteClassById(id : Long) = classService.deleteClassById(id)

    suspend fun createClass(classRequest: ClassRequest) = classService.createClass(classRequest)

    suspend fun updateClass(classRequest: ClassRequest) = classService.updateClass(classRequest)


}
