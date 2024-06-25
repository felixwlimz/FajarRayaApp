package com.fajarraya.app.pages.auth.register


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.model.User
import com.fajarraya.app.core.domain.usecase.auth.AuthUseCase
import com.fajarraya.app.models.UserType
import com.fajarraya.app.utils.Extensions



class RegisterViewModel(private val authUseCase: AuthUseCase) : ViewModel() {

    var nameInput by mutableStateOf("")
    private set

    var usernameInput by mutableStateOf("")

    var emailInput by mutableStateOf("")
        private set

    var passwordInput by mutableStateOf("")
        private set

    var isError by mutableStateOf(false)
        private set

    var errorText by mutableStateOf("")
        private set

    fun setName(name : String){
        nameInput = name
    }


    fun setUsername(username: String){
        usernameInput = username
    }

    fun setPassword(password : String){
        passwordInput = password
    }

    fun setEmail(email : String){
        emailInput = email
    }

    fun validateRegister(
        name : String,
        username: String,
        email: String,
        password: String,
        userType: UserType
    ){
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
                errorText= "Invalid email"
            }
            !passRegex.matches(password) -> {
                isError = true
                errorText = "Invalid Password "
            }
            else -> {
                val user = authUseCase.currentUser?.uid?.let {
                    User(userId = it,
                        email = email,
                        password = password,
                        name = name,
                        username = username,
                        superAdmin = userType
                    )
                } as User
                authUseCase.register(user)
            }

        }
    }

}