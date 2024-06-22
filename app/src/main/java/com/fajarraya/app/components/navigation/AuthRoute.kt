package com.fajarraya.app.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fajarraya.app.pages.auth.login.LoginPage
import com.fajarraya.app.pages.auth.register.RegisterPage

@Composable
fun AuthRoute(navHostController: NavHostController){

    NavHost(
        navController = navHostController,
        startDestination = AuthScreen.Login.route,
    ){

        composable(AuthScreen.Login.route){
            LoginPage(navHostController = navHostController)
        }

        composable(AuthScreen.Register.route){
            RegisterPage(navHostController = navHostController)
        }

    }

}