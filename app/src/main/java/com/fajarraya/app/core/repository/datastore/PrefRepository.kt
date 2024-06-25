package com.fajarraya.app.core.repository.datastore

import com.fajarraya.app.core.data.datastore.DarkModePreference
import io.reactivex.rxjava3.core.Flowable

class PrefRepository(private val darkModePreference: DarkModePreference) : IPrefRepository {
    override fun getTheme(): Flowable<Boolean> = darkModePreference.getTheme()

    override fun setTheme(isDarkMode : Boolean) : Unit = darkModePreference.setTheme(isDarkMode)
}