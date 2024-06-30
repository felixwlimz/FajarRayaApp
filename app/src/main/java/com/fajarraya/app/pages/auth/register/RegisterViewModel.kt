package com.fajarraya.app.pages.auth.register


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.trimmedLength
import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.model.User
import com.fajarraya.app.core.domain.usecase.auth.AuthUseCase
import com.fajarraya.app.models.UserType
import com.fajarraya.app.utils.Extensions
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


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
        userType: UserType,
        onCompleted: () -> Unit
    ){

        when{
            email.isEmpty() -> {
                isError = true
                errorText = "Email must not be empty"
            }
            password.isEmpty() -> {
                isError = true
                errorText = "Password must not be empty"
            }
            password.trimmedLength() < 8 -> {
                isError = true
                errorText= "Panjang password harus > 8 karakter"
            }
            else -> {
                val user = User(userId = null,
                    email = email,
                    password = password,
                    name = name,
                    username = username,
                    superAdmin = userType
                )
                authUseCase.register(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            onCompleted()
                        },
                        {
                            isError = true
                            errorText = it.message.toString()
                        }
                    );
            }
        }
    }

}