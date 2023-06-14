package com.una.FrontEndTango.Model

import java.util.*

data class Request(
    var id: Long? = null,
    var asset_id:Long? = null,
    var classroom_id: Long? = null,
    var date_hour:Date,
    var user_id:Long?=null,
)
data class RequestRequest(
    var id: Long? = null,
    var asset_id:Long? = null,
    var classroom_id: Long? = null,
    var date_hour:Date,
    var user_id:Long?=null,

    )
data class RequestResponse(
    var id: Long? = null,
    var asset_id:Long? = null,
    var classroom_id: Long? = null,
    var date_hour:Date,
    var user_id:Long?=null,

)
