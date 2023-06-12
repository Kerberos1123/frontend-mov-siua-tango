package com.una.FrontEndTango.Model

import java.util.*

data class Classroom(
    var title: String,
    var description: String,
)
data class ClassroomRequest(
    var id: Long? = null,
    var title: String,
    var description:String,
)
data class ClassroomResponse(
    var id: Long? = null,
    var title: String,
    var description:String,
)
