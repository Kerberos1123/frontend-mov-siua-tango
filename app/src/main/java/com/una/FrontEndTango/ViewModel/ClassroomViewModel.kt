package com.una.FrontEndTango.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.una.FrontEndTango.Model.ClassroomRequest
import com.una.FrontEndTango.Model.ClassroomResponse
import com.una.FrontEndTango.Repository.ClassroomRepository
import kotlinx.coroutines.*


sealed class StateClassroom{

    object Loading : StateClassroom()

    data class Success(val classroom: ClassroomResponse?) : StateClassroom()

    data class SuccessDelete(val deleted: Boolean?) : StateClassroom()

    data class SuccessList(val classroomList: List<ClassroomResponse>?) : StateClassroom()

    data class Error(val message: String) : StateClassroom()
}
class ClassroomViewModel  constructor(
    private val classroomRepository: ClassroomRepository,
) : ViewModel() {

    // this is just a way to keep the mutable LiveData private, so it can't be updated
    private val _state = MutableLiveData<StateClassroom>()
    val state: LiveData<StateClassroom> get() = _state

    private var job: Job? = null
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getClassroom(id: Long) {
        _state.value = StateClassroom.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = classroomRepository.getClassroomById(id)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateClassroom.Success(response.body())
                    else StateClassroom.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun findAllClassroom() {
        _state.value = StateClassroom.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = classroomRepository.getAllClassroom()
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateClassroom.SuccessList(response.body())
                    else StateClassroom.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun deleteClassroomById(id: Long) {
        _state.value = StateClassroom.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = classroomRepository.deleteClassroomById(id)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateClassroom.SuccessDelete(true)
                    else StateClassroom.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun createClassroom(classroomClassroom: ClassroomRequest) {
        _state.value = StateClassroom.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = classroomRepository.createClassroom(classroomClassroom)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    (if (response.isSuccessful) {
                        StateClassroom.Success(response.body() as ClassroomResponse)
                    } else {
                        StateClassroom.Error("Error : ${response.message()} ")
                        onError("Error : ${response.message()}")
                    }) as StateClassroom?
                )
            }
        }
    }

    fun updateClassroom(classroomClassroom: ClassroomRequest) {
        _state.value = StateClassroom.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = classroomRepository.updateClassroom(classroomClassroom)
            withContext(Dispatchers.Main) {
                _state.postValue(
                    if (response.isSuccessful) StateClassroom.Success(response.body())
                    else StateClassroom.Error("Error : ${response.message()} ")
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