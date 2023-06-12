package com.una.FrontEndTango.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.una.FrontEndTango.Repository.ClassroomRepository
import com.una.FrontEndTango.Service.ClassroomService



@Suppress("UNCHECKED_CAST")
class ClassroomViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ClassroomViewModel::class.java)) {
            ClassroomViewModel(
                classroomRepository = ClassroomRepository(
                    classroomService = ClassroomService.getInstance()
                )
            ) as T
        } else {
            throw IllegalArgumentException("ClassroomModel Not Found")
        }
    }
}