package com.una.FrontEndTango.Service

import com.una.FrontEndTango.Model.ClassResponse
import com.una.FrontEndTango.Model.ClassRequest
import retrofit2.Response
import retrofit2.http.*

interface ClassService {

    @GET("v1/classes")
    suspend fun getAllClasses() : Response<List<ClassResponse>>

    @GET("v1/classes/{id}")
    suspend fun getClassById(@Path("id") id: Long) : Response<ClassResponse>

    @DELETE("v1/classes/{id}")
    suspend fun deleteClassById(@Path("id") id: Long): Response<Void>

    @POST("v1/classes")
    suspend fun createClass(@Body classRequest: ClassRequest) : Response<ClassResponse>

    @PUT("v1/classes")
    suspend fun updateClass(@Body classRequest: ClassRequest) : Response<ClassResponse>

    companion object{
        private var classService : ClassService? = null
        fun getInstance() : ClassService {
            if (classService == null) {
                classService = ServiceBuilder.buildService(ClassService::class.java)
            }
            return classService!!
        }
    }
}