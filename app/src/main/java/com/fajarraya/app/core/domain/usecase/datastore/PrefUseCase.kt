package com.fajarraya.app.core.domain.usecase.datastore

import io.reactivex.rxjava3.core.Flowable

interface PrefUseCase {
    fun getTheme() : Flowable<Boolean>

    fun setTheme(isDarkMode : Boolean)
}