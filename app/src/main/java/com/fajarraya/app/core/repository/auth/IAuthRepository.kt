package com.fajarraya.app.core.repository.auth

import com.fajarraya.app.core.data.remote.UserResponse
import com.google.firebase.auth.FirebaseUser
import io.reactivex.rxjava3.core.Completable

interface IAuthRepository {

    val currentUser : FirebaseUser?
    fun login(email : String, password : String) : Completable
    fun register(userResponse: UserResponse) : Completable

    fun logout()

}