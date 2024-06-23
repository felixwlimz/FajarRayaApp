package com.fajarraya.app.core.data.remote

data class UserResponse(
    val userId : String,
    val name : String,
    val username : String,
    val password : String,
    val email : String,
    val isSuperAdmin : Boolean
)
