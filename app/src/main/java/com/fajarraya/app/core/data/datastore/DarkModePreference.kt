package com.fajarraya.app.core.data.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.rxjava3.RxDataStore
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi

class DarkModePreference(private val dataStore : RxDataStore<Preferences>) {

    private val THEME_KEY = booleanPreferencesKey("theme_setting")

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getTheme() : Flowable<Boolean> {
        return dataStore.data().map {
            it[THEME_KEY] ?: false
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun setTheme(isDarkMode : Boolean){
        dataStore.updateDataAsync{
           val pref = it.toMutablePreferences()
            pref[THEME_KEY] = isDarkMode
            Single.just(pref)
        }
    }


}