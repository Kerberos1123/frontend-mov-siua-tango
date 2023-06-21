package com.una.FrontEndTango.Model

import java.util.*

data class Classroom2(
    var id:Long? = null,
    var name: String,
    var id_teacher: Long? = null,
)
data class ClassroomRequest(
    var id:Long? = null,
    var name: String,
    var id_teacher: Long? = null,
)
data class ClassroomResponse(
    var id:Long? = null,
    var name: String,
    var id_teacher: Long? = null,
)
