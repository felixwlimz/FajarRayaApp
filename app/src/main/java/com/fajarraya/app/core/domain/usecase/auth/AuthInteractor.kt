package com.fajarraya.app.core.domain.usecase.auth

import com.fajarraya.app.core.data.remote.UserResponse
import com.fajarraya.app.core.domain.model.User
import com.fajarraya.app.core.repository.auth.IAuthRepository
import com.fajarraya.app.core.utils.DataMapper
import com.google.firebase.auth.FirebaseUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class AuthInteractor(private val authRepository: IAuthRepository) : AuthUseCase{

    override val currentUser: FirebaseUser?
        get() = authRepository.currentUser

    override fun userData(): Single<User> = authRepository.getUserData()

    override fun userDataByID(id: String): Single<User> = authRepository.getUserData()

    override fun login(email: String, password: String): Completable = authRepository.login(email, password)

    override fun register(user: User): Completable {
        return authRepository.register(mapper.mapFrom(user))
    }

    override fun logout() {
        authRepository.logout()
    }

    private val mapper = object : DataMapper<User, UserResponse>{
        override fun mapFrom(data: User): UserResponse {
            return UserResponse(
                name = data.name,
                email = data.email,
                userId = data.userId,
                password = data.password,
                username = data.username,
                superAdmin = data.superAdmin
            )
        }

    }



}