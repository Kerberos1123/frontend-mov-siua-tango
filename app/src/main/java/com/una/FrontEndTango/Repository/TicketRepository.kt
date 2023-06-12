package com.una.FrontEndTango.Repository

import com.una.FrontEndTango.Model.TicketRequest
import com.una.FrontEndTango.Service.TicketService

class TicketRepository constructor(
    private val ticketService: TicketService
){
    suspend fun getAllTicket() = ticketService.getAllTickets()

    suspend fun getTicketById(id : Long) = ticketService.getTicketById(id)

    suspend fun deleteTicketById(id : Long) = ticketService.deleteTicketById(id)

    suspend fun createTicket(ticketRequest: TicketRequest) = ticketService.createTicket(ticketRequest)

    suspend fun updateTicket(ticketRequest: TicketRequest) = ticketService.updateTicket(ticketRequest)


}
