package com.una.FrontEndTango.Repository

import com.una.FrontEndTango.Model.ReportRequest
import com.una.FrontEndTango.Service.ReportService

class ReportRepository constructor(
    private val reportService: ReportService
){
    suspend fun getAllReport() = reportService.getAllReports()

    suspend fun getReportById(id : Long) = reportService.getReportById(id)

    suspend fun deleteReportById(id : Long) = reportService.deleteReportById(id)

    suspend fun createReport(reportRequest: ReportRequest) = reportService.createReport(reportRequest)

    suspend fun updateReport(reportRequest: ReportRequest) = reportService.updateReport(reportRequest)


}
