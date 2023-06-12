package com.una.FrontEndTango.Model

import java.util.*

data class Class(
    var id:Long? =null,
    var id_classroom:Long? = null,
    var name: String,
    var id_teacher: Long? = null,
)
data class ClassRequest(
    var id:Long? =null,
    var id_classroom:Long? = null,
    var name: String,
    var id_teacher: Long? = null,
)
data class ClassResponse(
    var id:Long? =null,
    var id_classroom:Long? = null,
    var name: String,
    var id_teacher: Long? = null,
)
