package com.fajarraya.app.core.domain.model

import com.fajarraya.app.models.UserType

data class User(
    val userId : String?,
    val name : String,
    val username : String,
    val password : String,
    val email : String,
    val superAdmin : UserType
)