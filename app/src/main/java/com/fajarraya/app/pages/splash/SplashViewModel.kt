package com.fajarraya.app.pages.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.fajarraya.app.components.navigation.Screen
import com.fajarraya.app.core.domain.usecase.auth.AuthUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    fun checkLogin(navHostController: NavHostController){
        viewModelScope.launch{
            delay(1000)
            if(authUseCase.currentUser == null){
                navHostController.navigate(Screen.Login.route)
            }else{
                navHostController.navigate(Screen.Orders.OrderPage.route)
            }
        }

    }

}