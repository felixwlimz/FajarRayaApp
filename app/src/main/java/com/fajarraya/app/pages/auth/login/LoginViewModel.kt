package com.fajarraya.app.pages.auth.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel() : ViewModel() {

    private var _username : MutableState<String> = mutableStateOf("")
    val username : State<String> = _username

    private var _password : MutableState<String> = mutableStateOf("")
    val password : State<String> = _password

    fun setUsername(username: String){
        _username.value = username
    }

    fun setPassword(password : String){
        _password.value = password
    }

}