package com.fajarraya.app.core.domain.usecase.auth

import com.fajarraya.app.core.domain.model.User
import com.google.firebase.auth.FirebaseUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface AuthUseCase {

    val currentUser : FirebaseUser?

    fun userData() : Single<User>

    fun userDataByID(id:String) : Single<User>

    fun login(email : String, password : String) : Completable

    fun register(user : User) : Completable

    fun logout()

}