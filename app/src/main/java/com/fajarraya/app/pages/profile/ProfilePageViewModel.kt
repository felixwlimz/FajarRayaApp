package com.fajarraya.app.pages.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.fajarraya.app.core.domain.usecase.datastore.PrefUseCase

class ProfilePageViewModel(private val prefUseCase: PrefUseCase) : ViewModel() {

    var isChecked by mutableStateOf(false)
    private set

    fun setTheme(isDarkMode: Boolean){
        isChecked = isDarkMode
        prefUseCase.setTheme(isDarkMode)
    }

    fun getTheme() : LiveData<Boolean> = prefUseCase.getTheme().toLiveData()




}