package com.fajarraya.app.pages.auth.login


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.usecase.auth.AuthUseCase
import com.fajarraya.app.utils.Extensions


class LoginViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    var emailInput by mutableStateOf("")
    private set

    var passwordInput by mutableStateOf("")
    private set

    var isError by mutableStateOf(false)
        private set

    var errorText by mutableStateOf("")
    private set

    fun setEmail(email: String){
        emailInput = email
    }

    fun setPassword(password : String){
       passwordInput = password
    }



    fun validateLogin(email: String, password: String){
        val emailRegex = Extensions.useRegex("[a-zA-Z0–9._-]+@[a-z]+\\.+[a-z]+")
        val passRegex = Extensions.useRegex("^(?=.*[0–9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")

        when{
            email.isEmpty() -> {
                isError = true
                errorText = "Email must not be empty"
            }
            password.isEmpty() -> {
                isError = true
                errorText = "Password must not be empty"
            }
            !emailRegex.matches(email) -> {
                isError = true
                errorText = "Invalid email"
            }
            !passRegex.matches(password) -> {
                isError = true
                errorText = "Invalid Password"
            }
            else -> {
                authUseCase.login(email, password)
            }

        }
    }

}