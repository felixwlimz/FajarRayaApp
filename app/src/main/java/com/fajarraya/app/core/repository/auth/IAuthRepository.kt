package com.fajarraya.app.core.repository.auth

import com.fajarraya.app.core.data.remote.UserResponse
import com.fajarraya.app.core.domain.model.User
import com.google.firebase.auth.FirebaseUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IAuthRepository {

    val currentUser : FirebaseUser?
    fun login(email : String, password : String) : Completable
    fun register(userResponse: UserResponse) : Completable

    fun getUserData(): Single<User>

    fun logout()

}