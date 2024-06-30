package com.fajarraya.app.pages.auth.login


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.usecase.auth.AuthUseCase
import com.fajarraya.app.utils.Extensions
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


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



    fun validateLogin(email: String, password: String,onCompleted: () -> Unit){
        when{
            email.isEmpty() -> {
                isError = true
                errorText = "Email must not be empty"
            }
            password.isEmpty() -> {
                isError = true
                errorText = "Password must not be empty"
            }
            else -> {
                authUseCase.login(email, password)
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