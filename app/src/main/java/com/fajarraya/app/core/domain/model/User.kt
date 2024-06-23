package com.fajarraya.app.core.domain.model

data class User(
    val userId : String,
    val name : String,
    val username : String,
    val password : String,
    val email : String,
    val isSuperAdmin : Boolean
)