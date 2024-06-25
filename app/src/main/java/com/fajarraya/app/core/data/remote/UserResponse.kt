package com.fajarraya.app.core.data.remote

import com.fajarraya.app.models.UserType

data class UserResponse(
    val userId : String,
    val name : String,
    val username : String,
    val password : String,
    val email : String,
    val superAdmin : UserType
)
