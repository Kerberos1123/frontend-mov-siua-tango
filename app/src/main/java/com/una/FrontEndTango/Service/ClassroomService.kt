package com.una.FrontEndTango.Service

import com.una.FrontEndTango.Model.ClassroomRequest
import com.una.FrontEndTango.Model.ClassroomResponse

import retrofit2.Response
import retrofit2.http.*


interface ClassroomService {

    @GET("v1/classrooms")
    suspend fun getAllClassrooms() : Response<List<ClassroomResponse>>

    @GET("v1/classrooms/{id}")
    suspend fun getClassroomById(@Path("id") id: Long) : Response<ClassroomResponse>

    @DELETE("v1/classrooms/{id}")
    suspend fun deleteClassroomById(@Path("id") id: Long): Response<Void>

    @POST("v1/classrooms")
    suspend fun createClassroom(@Body taskRequest: ClassroomRequest) : Response<ClassroomResponse>

    @PUT("v1/classrooms")
    suspend fun updateClassroom(@Body taskRequest: ClassroomRequest) : Response<ClassroomResponse>

    companion object{
        private var classroomService : ClassroomService? = null
        fun getInstance() : ClassroomService {
            if (classroomService == null) {
                classroomService = ServiceBuilder.buildService(ClassroomService::class.java)
            }
            return classroomService!!
        }
    }
}
