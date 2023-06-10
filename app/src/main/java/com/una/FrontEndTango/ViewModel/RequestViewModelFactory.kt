package com.una.FrontEndTango.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.una.FrontEndTango.Repository.RequestRepository
import com.una.FrontEndTango.Service.RequestService



@Suppress("UNCHECKED_CAST")
class RequestViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RequestViewModel::class.java)) {
            RequestViewModel(
                requestRepository = RequestRepository(
                    requestService = RequestService.getInstance()
                )
            ) as T
        } else {
            throw IllegalArgumentException("RequestModel Not Found")
        }
    }
}