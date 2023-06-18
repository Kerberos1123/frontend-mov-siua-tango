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
    //var id_classroom:Long? = null,
    //var name: String,
    //var id_teacher: Long? = null,

    val className: String,
    val classTeacher: ClassTeacher,
    val classClassroom: ClassClassroom,
    val createDate: String

)
data class ClassTeacher(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val roleList: List<RoleTeacher>,

)
data class RoleTeacher(
    val id: Long,
    val name: String,
    val privileges: List<String>? // Assuming privileges is a list of strings
)

data class ClassClassroom(
    val id: Long,
    val classroomName: String,
    // Add other properties if needed
)