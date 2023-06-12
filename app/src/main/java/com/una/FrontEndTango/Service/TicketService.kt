package com.una.FrontEndTango.Service

import com.una.FrontEndTango.Model.TicketRequest
import com.una.FrontEndTango.Model.TicketResponse

import retrofit2.Response
import retrofit2.http.*


interface TicketService {

    @GET("v1/tickets")
    suspend fun getAllTickets() : Response<List<TicketResponse>>

    @GET("v1/tickets/{id}")
    suspend fun getTicketById(@Path("id") id: Long) : Response<TicketResponse>

    @DELETE("v1/tickets/{id}")
    suspend fun deleteTicketById(@Path("id") id: Long): Response<Void>

    @POST("v1/tickets")
    suspend fun createTicket(@Body taskRequest: TicketRequest) : Response<TicketResponse>

    @PUT("v1/tickets")
    suspend fun updateTicket(@Body taskRequest: TicketRequest) : Response<TicketResponse>

    companion object{
        private var ticketService : TicketService? = null
        fun getInstance() : TicketService {
            if (ticketService == null) {
                ticketService = ServiceBuilder.buildService(TicketService::class.java)
            }
            return ticketService!!
        }
    }
}
