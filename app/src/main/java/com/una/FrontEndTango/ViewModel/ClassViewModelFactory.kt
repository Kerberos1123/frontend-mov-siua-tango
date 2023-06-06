package com.una.FrontEndTango.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.una.FrontEndTango.Repository.ClassRepository
import com.una.FrontEndTango.Service.ClassService


@Suppress("UNCHECKED_CAST")
class ClassViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ClassViewModel::class.java)) {
            ClassViewModel(
                classRepository = ClassRepository(
                    classService = ClassService.getInstance()
                )
            ) as T
        } else {
            throw IllegalArgumentException("ClassModel Not Found")
        }
    }
}