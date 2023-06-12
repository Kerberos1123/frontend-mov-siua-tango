package com.una.FrontEndTango.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.una.FrontEndTango.Model.ReportRequest
import com.una.FrontEndTango.Model.ReportResponse
import com.una.FrontEndTango.Repository.ReportRepository
import kotlinx.coroutines.*


sealed class StateReport{

    object Loading : StateReport()

    data class Success(val report: ReportResponse?) : StateReport()

    data class SuccessDelete(val deleted: Boolean?) : StateReport()

    data class SuccessList(val reportList: List<ReportResponse>?) : StateReport()

    data class Error(val message: String) : StateReport()
}
class ReportViewModel  constructor(
    private val reportRepository: ReportRepository,
) : ViewModel() {

    // this is just a way to keep the mutable LiveData private, so it can't be updated
    private val _state = MutableLiveData<StateReport>()
    val state: LiveData<StateReport> get() = _state

    private var job: Job? = null
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getReport(id: Long) {
        _state.value = StateReport.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = reportRepository.getReportById(id)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateReport.Success(response.body())
                    else StateReport.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun findAllReport() {
        _state.value = StateReport.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = reportRepository.getAllReport()
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateReport.SuccessList(response.body())
                    else StateReport.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun deleteReportById(id: Long) {
        _state.value = StateReport.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = reportRepository.deleteReportById(id)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateReport.SuccessDelete(true)
                    else StateReport.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun createReport(reportReport: ReportRequest) {
        _state.value = StateReport.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = reportRepository.createReport(reportReport)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    (if (response.isSuccessful) {
                        StateReport.Success(response.body() as ReportResponse)
                    } else {
                        StateReport.Error("Error : ${response.message()} ")
                        onError("Error : ${response.message()}")
                    }) as StateReport?
                )
            }
        }
    }

    fun updateReport(reportReport: ReportRequest) {
        _state.value = StateReport.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = reportRepository.updateReport(reportReport)
            withContext(Dispatchers.Main) {
                _state.postValue(
                    if (response.isSuccessful) StateReport.Success(response.body())
                    else StateReport.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}