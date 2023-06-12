package com.una.FrontEndTango.Model

import java.util.*

data class TaskRequest(
    var id: Long? = null,
    var create_date: Date,
    var due_date: Date,
    var notes: String,
    var title: String,
    var priority_id: Long?=null,
    var status_id: Long?=null,
    var user_id:Long?=null,
)

data class TaskResponse (
    var id: Long? = null,
    var create_date: Date,
    var due_date: Date,
    var notes: String,
    var title: String,
    var priority_id: Long?=null,
    var status_id: Long?=null,
    var user_id:Long?=null,
)