package com.fajarraya.app.core.data.remote

import com.fajarraya.app.models.UserType

data class UserResponse(
    var userId : String?,
    val name : String,
    val username : String,
    var password : String,
    val email : String,
    val superAdmin : UserType
)
