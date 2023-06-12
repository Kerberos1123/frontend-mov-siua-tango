package com.una.FrontEndTango.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.una.FrontEndTango.Repository.TicketRepository
import com.una.FrontEndTango.Service.TicketService



@Suppress("UNCHECKED_CAST")
class TicketViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TicketViewModel::class.java)) {
            TicketViewModel(
                ticketRepository = TicketRepository(
                    ticketService = TicketService.getInstance()
                )
            ) as T
        } else {
            throw IllegalArgumentException("TicketModel Not Found")
        }
    }
}