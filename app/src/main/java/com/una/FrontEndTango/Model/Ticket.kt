package com.una.FrontEndTango.Model

import java.util.*

data class Ticket(
    var title: String,
    var description: String,
)
data class TicketRequest(
    var id: Long? = null,
    var title: String,
    var description:String,
)
data class TicketResponse(
    var id: Long? = null,
    var title: String,
    var description:String,
)
