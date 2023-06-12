package com.una.FrontEndTango.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.una.FrontEndTango.Repository.ReportRepository
import com.una.FrontEndTango.Service.ReportService



@Suppress("UNCHECKED_CAST")
class ReportViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ReportViewModel::class.java)) {
            ReportViewModel(
                reportRepository = ReportRepository(
                    reportService = ReportService.getInstance()
                )
            ) as T
        } else {
            throw IllegalArgumentException("ReportModel Not Found")
        }
    }
}