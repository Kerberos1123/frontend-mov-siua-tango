package com.una.FrontEndTango.Repository

import com.una.FrontEndTango.Model.ClassroomRequest
import com.una.FrontEndTango.Service.ClassroomService

class ClassroomRepository constructor(
    private val classroomService: ClassroomService
){
    suspend fun getAllClassroom() = classroomService.getAllClassrooms()

    suspend fun getClassroomById(id : Long) = classroomService.getClassroomById(id)

    suspend fun deleteClassroomById(id : Long) = classroomService.deleteClassroomById(id)

    suspend fun createClassroom(classroomRequest: ClassroomRequest) = classroomService.createClassroom(classroomRequest)

    suspend fun updateClassroom(classroomRequest: ClassroomRequest) = classroomService.updateClassroom(classroomRequest)


}
