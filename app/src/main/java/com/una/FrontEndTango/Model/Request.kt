package com.una.FrontEndTango.Model

import java.util.*

data class Request(
    var id: Long? = null,
    var asset_id:Long? = null,
    var classroom_id: Long? = null,
    var date_hour:Date,
    var user_id:Long?=null,
)
data class RequestRequest(
    var id: Long? = null,
    var asset_id:Long? = null,
    var classroom_id: Long? = null,
    var date_hour:Date,
    var user_id:Long?=null,

    )
data class RequestResponse(
    var id: Long? = null,
    //var assets_id:Long? = null,
    var classroomId: Long? = null,
   // var dateHour:Date,
   // var user_id:Long?=null,


    //se ocupa deserializar
    val assets: AssetData,
    val user: UserData,
    val state: StateData

)


//Deserializacion del Json de RequestResponse
data class AssetData(
    val id: Long,
    val assetName: String,
    val assetType: AssetTypeData,
    val available: Boolean
)

data class AssetTypeData(
    val id: Long,
    val name: String?
)

data class UserData(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val enabled: Boolean,
    val tokenExpired: Boolean,
    val createDate: String,
    val roleList: List<RoleData>
)

data class RoleData(
    val id: Long,
    val name: String,
    val privileges: List<String>?
)

data class StateData(
    val id: Long,
    val name: String
)
