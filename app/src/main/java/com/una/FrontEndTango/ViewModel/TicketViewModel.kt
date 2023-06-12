package com.una.FrontEndTango.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.una.FrontEndTango.Model.TicketRequest
import com.una.FrontEndTango.Model.TicketResponse
import com.una.FrontEndTango.Repository.TicketRepository
import kotlinx.coroutines.*


sealed class StateTicket{

    object Loading : StateTicket()

    data class Success(val ticket: TicketResponse?) : StateTicket()

    data class SuccessDelete(val deleted: Boolean?) : StateTicket()

    data class SuccessList(val ticketList: List<TicketResponse>?) : StateTicket()

    data class Error(val message: String) : StateTicket()
}
class TicketViewModel  constructor(
    private val ticketRepository: TicketRepository,
) : ViewModel() {

    // this is just a way to keep the mutable LiveData private, so it can't be updated
    private val _state = MutableLiveData<StateTicket>()
    val state: LiveData<StateTicket> get() = _state

    private var job: Job? = null
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getTicket(id: Long) {
        _state.value = StateTicket.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = ticketRepository.getTicketById(id)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateTicket.Success(response.body())
                    else StateTicket.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun findAllTicket() {
        _state.value = StateTicket.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = ticketRepository.getAllTicket()
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateTicket.SuccessList(response.body())
                    else StateTicket.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun deleteTicketById(id: Long) {
        _state.value = StateTicket.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = ticketRepository.deleteTicketById(id)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    if (response.isSuccessful) StateTicket.SuccessDelete(true)
                    else StateTicket.Error("Error : ${response.message()} ")
                )
            }
        }
    }

    fun createTicket(ticketTicket: TicketRequest) {
        _state.value = StateTicket.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = ticketRepository.createTicket(ticketTicket)
            withContext(Dispatchers.Main) {
                // if you're using postValue I don't think you need to switch to Dispatchers.Main?
                _state.postValue(
                    // when you get a response, the state is now either Success or Error
                    (if (response.isSuccessful) {
                        StateTicket.Success(response.body() as TicketResponse)
                    } else {
                        StateTicket.Error("Error : ${response.message()} ")
                        onError("Error : ${response.message()}")
                    }) as StateTicket?
                )
            }
        }
    }

    fun updateTicket(ticketTicket: TicketRequest) {
        _state.value = StateTicket.Loading
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = ticketRepository.updateTicket(ticketTicket)
            withContext(Dispatchers.Main) {
                _state.postValue(
                    if (response.isSuccessful) StateTicket.Success(response.body())
                    else StateTicket.Error("Error : ${response.message()} ")
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