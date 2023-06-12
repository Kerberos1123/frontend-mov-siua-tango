package com.una.FrontEndTango.Service

import com.una.FrontEndTango.Model.ReportRequest
import com.una.FrontEndTango.Model.ReportResponse

import retrofit2.Response
import retrofit2.http.*


interface ReportService {

    @GET("v1/reports")
    suspend fun getAllReports() : Response<List<ReportResponse>>

    @GET("v1/reports/{id}")
    suspend fun getReportById(@Path("id") id: Long) : Response<ReportResponse>

    @DELETE("v1/reports/{id}")
    suspend fun deleteReportById(@Path("id") id: Long): Response<Void>

    @POST("v1/reports")
    suspend fun createReport(@Body taskRequest: ReportRequest) : Response<ReportResponse>

    @PUT("v1/reports")
    suspend fun updateReport(@Body taskRequest: ReportRequest) : Response<ReportResponse>

    companion object{
        private var reportService : ReportService? = null
        fun getInstance() : ReportService {
            if (reportService == null) {
                reportService = ServiceBuilder.buildService(ReportService::class.java)
            }
            return reportService!!
        }
    }
}
