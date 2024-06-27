package com.fajarraya.app.core.domain.usecase.datastore

import com.fajarraya.app.core.repository.datastore.IPrefRepository
import io.reactivex.rxjava3.core.Flowable


class PrefInteractor(private val prefRepository: IPrefRepository) : PrefUseCase {
    override fun getTheme(): Flowable<Boolean> = prefRepository.getTheme()

    override fun setTheme(isDarkMode: Boolean) : Unit = prefRepository.setTheme(isDarkMode)
}