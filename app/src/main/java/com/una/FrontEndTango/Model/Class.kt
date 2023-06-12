package com.una.FrontEndTango.Model

import java.util.*

data class Class(
    var title: String,
    var description: String,
)
data class ClassRequest(
    var id: Long? = null,
    var title: String,
    var description:String,
)
data class ClassResponse(
    var id: Long? = null,
    var title: String,
    var description:String,
)
