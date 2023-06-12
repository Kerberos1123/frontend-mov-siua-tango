package com.una.FrontEndTango.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.una.FrontEndTango.Model.RequestRequest
import com.una.FrontEndTango.Model.RequestResponse
import com.una.FrontEndTango.Repository.RequestRepository
import kotlinx.coroutines.*


sealed class StateRequest{

    object Loading : StateRequest()

    data class Success(val request: RequestResponse?) : StateRequest()

    data class SuccessDelete(val deleted: Boolean?) : StateRequest()

    data class SuccessList(val requestList: List<RequestResponse>?) : StateRequest()

    data class Error(val message: String) : StateRequest()
}
class RequestViewModel  constructor(
    private val requestRepository: RequestRepository,
) : ViewModel() {

    // this is just a way to keep the mutable LiveData private, so it can't be updated
    private val _state = MutableLiveData<StateRequest>()
    val state: LiveData<StateRequest> get() = _state

    private var job: Job? = null
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getRequest(id: Long) {
        _state.value = StateRequest.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = requestRepository.getRequestById(id)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateRequest.Success(response.body())
                    else StateRequest.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun findAllRequest() {
        _state.value = StateRequest.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = requestRepository.getAllRequest()
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateRequest.SuccessList(response.body())
                    else StateRequest.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun deleteRequestById(id: Long) {
        _state.value = StateRequest.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = requestRepository.deleteRequestById(id)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateRequest.SuccessDelete(true)
                    else StateRequest.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun createRequest(requestRequest: RequestRequest) {
        _state.value = StateRequest.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = requestRepository.createRequest(requestRequest)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    (if (response.isSuccessful) {
                        StateRequest.Success(response.body() as RequestResponse)
                    } else {
                        StateRequest.Error("Error : ${response.message()} ")
                        onError("Error : ${response.message()}")
                    }) as StateRequest?
                )
            }
        }
    }

    fun updateRequest(requestRequest: RequestRequest) {
        _state.value = StateRequest.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = requestRepository.updateRequest(requestRequest)
            withContext(Dispatchers.Main) {
               _state.postValue(
                    if (response.isSuccessful) StateRequest.Success(response.body())
                    else StateRequest.Error("Error : ${response.message()} ")
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