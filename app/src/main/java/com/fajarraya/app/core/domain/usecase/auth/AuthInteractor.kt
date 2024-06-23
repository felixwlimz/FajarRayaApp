package com.fajarraya.app.core.domain.usecase.auth

import com.fajarraya.app.core.domain.model.User
import com.fajarraya.app.core.repository.auth.IAuthRepository
import com.fajarraya.app.utils.DataMapper
import com.google.firebase.auth.FirebaseUser
import io.reactivex.rxjava3.core.Completable

class AuthInteractor(private val authRepository: IAuthRepository) : AuthUseCase{

    override val currentUser: FirebaseUser?
        get() = authRepository.currentUser
    override fun login(email: String, password: String): Completable = authRepository.login(email, password)

    override fun register(user : User): Completable {
        val userResponse = DataMapper.mapUserDomainToResponse(user)
        return authRepository.register(userResponse)
    }

    override fun logout() {
        authRepository.logout()
    }


}