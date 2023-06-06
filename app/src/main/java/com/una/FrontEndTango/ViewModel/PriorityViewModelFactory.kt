package com.una.FrontEndTango.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.una.FrontEndTango.Repository.PriorityRepository
import com.una.FrontEndTango.Service.PriorityService

@Suppress("UNCHECKED_CAST")
class PriorityViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PriorityViewModel::class.java)) {
            PriorityViewModel(
                priorityRepository = PriorityRepository(
                    priorityService = PriorityService.getInstance()
                )
            ) as T
        } else {
            throw IllegalArgumentException("PriorityModel Not Found")
        }
    }
}