package com.fajarraya.app.core.data.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.rxjava3.RxDataStore
import com.fajarraya.app.models.UserType
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi

class RolePreference (private val dataStore : RxDataStore<Preferences>) {

    private val ROLE_KEY = stringPreferencesKey("role")

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getRole() : Flowable<UserType> {
        return dataStore.data().map {
            if(it[ROLE_KEY] == null){
                UserType.EMPLOYEE
            }else if(it[ROLE_KEY] == UserType.ADMIN.name){
                UserType.ADMIN
            }else{
                UserType.EMPLOYEE
            }

        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun setRole(userType:UserType){
        dataStore.updateDataAsync{
            val pref = it.toMutablePreferences()
            pref[ROLE_KEY] = userType.name
            Single.just(pref)
        }
    }


}