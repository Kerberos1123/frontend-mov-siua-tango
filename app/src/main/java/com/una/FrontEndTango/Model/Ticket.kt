package com.una.FrontEndTango.Model

import java.util.*

data class Ticket(
    var id: Long?=null,
    var asset_type_id: Long?=null,
    var ticket_reason_id:Long?=null,
    var user_id:Long?=null,
)
data class TicketRequest(
    var id: Long?=null,
    var asset_type_id: Long?=null,
    var ticket_reason_id:Long?=null,
    var user_id:Long?=null,
)
data class TicketResponse(
    var id: Long?=null,
    var asset_type_id: Long?=null,
    var ticket_reason_id:Long?=null,
    var user_id:Long?=null,
)
