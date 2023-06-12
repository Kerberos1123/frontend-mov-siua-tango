package com.una.FrontEndTango.Model

import java.util.*

data class User(
    var title: String,
    var description: String,
)
data class UserRequest(
    var id: Long? = null,
    var title: String,
    var description:String,
)
data class UserResponse(
    var id: Long? = null,
    var title: String,
    var description:String,
)
