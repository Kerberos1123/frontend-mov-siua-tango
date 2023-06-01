package com.una.FrontEndTango.Model

data class Priority(
    var id: Long? = null,
    var label: String? = null,
) {
    override fun toString(): String {
        return "$label"
    }
}