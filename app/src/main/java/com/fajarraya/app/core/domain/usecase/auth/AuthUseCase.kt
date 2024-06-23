package com.fajarraya.app.core.domain.usecase.auth

import com.fajarraya.app.core.domain.model.User
import com.google.firebase.auth.FirebaseUser
import io.reactivex.rxjava3.core.Completable

interface AuthUseCase {

    val currentUser : FirebaseUser?

    fun login(email : String, password : String) : Completable

    fun register(user : User) : Completable

    fun logout()

}