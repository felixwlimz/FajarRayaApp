package com.fajarraya.app.pages.auth.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.usecase.auth.AuthUseCase
import com.fajarraya.app.utils.Extensions

class LoginViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    private var _email : MutableState<String> = mutableStateOf("")
    val email : State<String> = _email

    private var _password : MutableState<String> = mutableStateOf("")
    val password : State<String> = _password

    private var _isError : MutableState<Boolean> = mutableStateOf(false)
    val isError : State<Boolean> = _isError

    fun setEmail(email: String){
        _email.value = email
    }

    fun setPassword(password : String){
        _password.value = password
    }

    fun handleLogin(email: String, password: String){
        validateLogin(email, password)
        authUseCase.login(email, password)
    }


    private fun validateLogin(email: String, password: String){
        when{
            email.isEmpty() -> {
                _isError.value = true
            }
            password.isEmpty() -> {
                _isError.value = true
            }
            email != authUseCase.currentUser?.email!! -> {
                _isError.value = true
            }
            password.length < 8 -> {
                _isError.value = true
            }
            !Extensions.useRegex("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}").matches(email) -> {
               _isError.value = true
            }

        }
    }

}