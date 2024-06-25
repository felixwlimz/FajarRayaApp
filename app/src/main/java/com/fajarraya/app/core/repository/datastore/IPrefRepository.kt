package com.fajarraya.app.core.repository.datastore

import io.reactivex.rxjava3.core.Flowable

interface IPrefRepository {

    fun getTheme() : Flowable<Boolean>

    fun setTheme(isDarkMode : Boolean)
}