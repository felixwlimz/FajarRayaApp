package com.fajarraya.app.core.repository.auth

import com.fajarraya.app.core.data.remote.UserResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.rxjava3.core.Completable


class AuthRepository(
    private val firebaseAuth : FirebaseAuth, private val firebaseDatabase : FirebaseDatabase) : IAuthRepository {

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override fun login(
        email: String,
        password: String
    ): Completable {
        return Completable.create { emitter ->
           val response = firebaseAuth.signInWithEmailAndPassword(email, password)
            response.addOnCompleteListener {
              if(it.isSuccessful){
                  emitter.onComplete()
              } else {
                  emitter.onError(Exception("Failed to Login"))
              }
            }
        }

    }

    override fun register(
        userResponse: UserResponse
    ): Completable {
        return Completable.create { emitter ->
            val register = firebaseAuth.createUserWithEmailAndPassword(userResponse.email, userResponse.password)
            firebaseDatabase.getReference("users")
                .child(firebaseAuth.uid!!)
                .setValue(userResponse)

            register.addOnCompleteListener {
                if(it.isSuccessful){
                    emitter.onComplete()
                } else {
                    emitter.onError(Exception("Failed to Register"))
                }
            }
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }


}