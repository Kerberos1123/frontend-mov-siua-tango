package com.una.FrontEndTango.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.una.FrontEndTango.Model.ClassRequest
import com.una.FrontEndTango.Model.ClassResponse
import com.una.FrontEndTango.Repository.ClassRepository
import kotlinx.coroutines.*


sealed class StateTask { //CREO QUE ESTO SE DEBE CAMBIAR A STATECLASS

    object Loading : StateTask()

    data class Success(val classs: ClassResponse?) : StateTask()

    data class SuccessDelete(val deleted: Boolean?) : StateTask()

    data class SuccessList(val classList: List<ClassResponse>?) : StateTask()

    data class Error(val message: String) : StateTask()
}

class ClassViewModel constructor(
    private val classRepository: ClassRepository,
) : ViewModel() {

    // this is just a way to keep the mutable LiveData private, so it can't be updated
    private val _state = MutableLiveData<StateTask>()
    val state: LiveData<StateTask> get() = _state

    private var job: Job? = null
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getClass(id: Long) {
        _state.value = StateTask.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = classRepository.getClassById(id)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateTask.Success(response.body())
                    else StateTask.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun findAllClass() {
        _state.value = StateTask.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = classRepository.getAllClass()
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateTask.SuccessList(response.body())
                    else StateTask.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun deleteClassById(id: Long) {
        _state.value = StateTask.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = classRepository.deleteClassById(id)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateTask.SuccessDelete(true)
                    else StateTask.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun createClass(classRequest: ClassRequest) {
        _state.value = StateTask.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = classRepository.createClass(classRequest)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    (if (response.isSuccessful) {
                        StateTask.Success(response.body() as ClassResponse)
                    } else {
                        StateTask.Error("Error : ${response.message()} ")
                        onError("Error : ${response.message()}")
                    }) as StateTask?
                )
            }
        }
    }

    fun updateTask(classRequest: ClassRequest) {
        _state.value = StateTask.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = classRepository.updateClass(classRequest)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateTask.Success(response.body())
                    else StateTask.Error("Error : ${response.message()} ")
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