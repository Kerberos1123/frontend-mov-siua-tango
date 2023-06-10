package com.una.FrontEndTango.Model

import java.util.*

data class Request(
    var title: String,
    var description:String
)
data class RequestRequest(
    var id: Long? = null,
    var asset_id:Long? = null,
    var classroom_id: Long? = null,
    var user_id:Long? = null,

    var item_name:String? = null,
    var classroom_name:String? = null,
    var user_name:String? = null,
    var user_role:String? = null,

    )
data class RequestResponse(
    var id: Long? = null,
    var asset_id:Long? = null,
    var classroom_id: Long? = null,
    var user_id:Long? = null,

    var item_name:String? = null,
    var classroom_name:String? = null,
    var user_name:String? = null,
    var user_role:String? = null,

)
