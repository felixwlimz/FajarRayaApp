package com.fajarraya.app.core.repository.auth

import android.util.Log
import com.fajarraya.app.core.data.remote.UserResponse
import com.fajarraya.app.core.domain.model.User
import com.fajarraya.app.core.repository.datastore.RoleRepository
import com.fajarraya.app.models.UserType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class AuthRepository(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore,
    private val roleRepository: RoleRepository
) : IAuthRepository {

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override fun login(
        email: String, password: String
    ): Completable {
        return Completable.create { emitter ->
            val response = firebaseAuth.signInWithEmailAndPassword(email, password)
            response.addOnCompleteListener {
                if (it.isSuccessful) {
                    getUserData()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            {
                                roleRepository.setRole(it.superAdmin)
                                emitter.onComplete()
                            },
                            {
                                emitter.onError(Exception("Error Occured"))
                            }
                        )
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
            firebaseAuth.createUserWithEmailAndPassword(userResponse.email, userResponse.password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {

//                    firebaseDatabase.getReference("users")
//                        .child(firebaseAuth.currentUser!!.uid)
//                        .setValue(userResponse.apply{
//                            userId = firebaseAuth.currentUser!!.uid
//                        }) // <- Ini firebase Realtime Database, Bukan Firestore

                        firebaseFirestore.collection("users")
                            .document(firebaseAuth.currentUser!!.uid).set(userResponse.apply {
                                userId = firebaseAuth.currentUser!!.uid
                                password = ""
                            }).addOnSuccessListener { documentReference ->
                                emitter.onComplete()
                            }.addOnFailureListener { e ->
                                emitter.onError(Exception("Failed to Register"))
                            }
                    } else {
                        emitter.onError(Exception("Failed to Register"))
                    }
                }
        }
    }

    override fun getUserData(): Single<User> {
        return Single.create { emitter ->

            if (currentUser == null) {
                emitter.onError(Exception("Not Authenticated"))
            }

            firebaseFirestore.collection("users").document(currentUser!!.uid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        Log.d("FirebaseFirestore", "DocumentSnapshot data: ${document.data}")
                        val user = User(
                            userId = document.getString("userId")!!,
                            email = document.getString("email")!!,
                            password = document.getString("password")!!,
                            username = document.getString("username")!!,
                            name = document.getString("name")!!,
                            superAdmin = UserType.valueOf(document.getString("superAdmin")!!),
                        )
                        emitter.onSuccess(user)
                    } else {
                        emitter.onError(Exception("Document Not Found"))
                    }

                }
                .addOnFailureListener {
                    it.printStackTrace()
                }
        }


    }

    override fun getUserDataByID(id: String): Single<User> {
        return Single.create { emitter ->

            firebaseFirestore.collection("users").document(id).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        Log.d("FirebaseFirestore", "DocumentSnapshot data: ${document.data}")
                        val user = User(
                            userId = document.getString("userId")!!,
                            email = document.getString("email")!!,
                            password = document.getString("password")!!,
                            username = document.getString("username")!!,
                            name = document.getString("name")!!,
                            superAdmin = UserType.valueOf(document.getString("superAdmin")!!),
                        )
                        emitter.onSuccess(user)
                    } else {
                        emitter.onError(Exception("Document Not Found"))
                    }

                }
                .addOnFailureListener {
                    it.printStackTrace()
                }
        }


    }

    override fun logout() {
        firebaseAuth.signOut()
        roleRepository.setRole(UserType.EMPLOYEE)
    }


}