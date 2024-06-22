package com.fajarraya.app.components.navigation

sealed class AuthScreen(val route : String) {
    data object Login : AuthScreen("Login")
    data object Register : AuthScreen("Register")
}