package com.una.FrontEndTango.ViewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.una.FrontEndTango.Model.ClassRequest
import com.una.FrontEndTango.Model.ClassResponse
import com.una.FrontEndTango.Repository.ClassRepository
import kotlinx.coroutines.*


sealed class StateClass { //CREO QUE ESTO SE DEBE CAMBIAR A STATECLASS

    object Loading : StateClass()

    data class Success(val classs: ClassResponse?) : StateClass()

    data class SuccessDelete(val deleted: Boolean?) : StateClass()

    data class SuccessList(val classList: List<ClassResponse>?) : StateClass()

    data class Error(val message: String) : StateClass()
}

class ClassViewModel constructor(
    private val classRepository: ClassRepository,
) : ViewModel() {

    // this is just a way to keep the mutable LiveData private, so it can't be updated
    private val _state = MutableLiveData<StateClass>()
    val state: LiveData<StateClass> get() = _state

    private var job: Job? = null
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getClass(id: Long) {
        _state.value = StateClass.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = classRepository.getClassById(id)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateClass.Success(response.body())
                    else StateClass.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun findAllClass() {
        _state.value = StateClass.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = classRepository.getAllClass()
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateClass.SuccessList(response.body())
                    else StateClass.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun deleteClassById(id: Long) {
        _state.value = StateClass.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = classRepository.deleteClassById(id)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateClass.SuccessDelete(true)
                    else StateClass.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun createClass(classRequest: ClassRequest) {
        _state.value = StateClass.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = classRepository.createClass(classRequest)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    (if (response.isSuccessful) {
                        StateClass.Success(response.body() as ClassResponse)
                    } else {
                        StateClass.Error("Error : ${response.message()} ")
                        onError("Error : ${response.message()}")
                    }) as StateClass?
                )
            }
        }
    }

    fun updateClass(classRequest: ClassRequest) {
        _state.value = StateClass.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = classRepository.updateClass(classRequest)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateClass.Success(response.body())
                    else StateClass.Error("Error : ${response.message()} ")
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