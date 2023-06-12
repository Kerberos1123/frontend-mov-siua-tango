package com.una.FrontEndTango.Model

import android.provider.ContactsContract.CommonDataKinds.Email
import java.util.*

data class User(
    var id: Long?=null,
    var create_date: Date,
    var email: String,
    var enabled: Boolean,
    var first_name:String,
    var last_name:String,
    var password:String,
    var token_expired:Boolean,
)
data class UserRequest(
    var id: Long?=null,
    var create_date: Date,
    var email: String,
    var enabled: Boolean,
    var first_name:String,
    var last_name:String,
    var password:String,
    var token_expired:Boolean,
)
data class UserResponse(
    var id: Long?=null,
    var create_date: Date,
    var email: String,
    var enabled: Boolean,
    var first_name:String,
    var last_name:String,
    var password:String,
    var token_expired:Boolean,
)
