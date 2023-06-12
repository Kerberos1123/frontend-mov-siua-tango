package com.una.FrontEndTango.Model

import java.util.*

data class Report(
    var title: String,
    var description: String,
)
data class ReportRequest(
    var id: Long? = null,
    var title: String,
    var description:String,
)
data class ReportResponse(
    var id: Long? = null,
    var title: String,
    var description:String,
)
