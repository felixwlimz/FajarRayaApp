package com.fajarraya.app.core.repository.datastore

import com.fajarraya.app.core.data.datastore.RolePreference
import com.fajarraya.app.models.UserType
import io.reactivex.rxjava3.core.Flowable

class RoleRepository (private val rolePreference: RolePreference) {
    fun getRole(): Flowable<UserType> = rolePreference.getRole()

    fun setRole(userType: UserType) = rolePreference.setRole(userType)
}