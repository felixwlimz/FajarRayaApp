package com.fajarraya.app.pages.profile.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.model.User
import com.fajarraya.app.core.domain.usecase.auth.AuthUseCase
import com.fajarraya.app.core.domain.usecase.datastore.PrefUseCase
import com.fajarraya.app.models.UserType
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.androidx.compose.get

class ProfileDetailViewModel(private val useCase: AuthUseCase): ViewModel() {

    var userdata by mutableStateOf(User("","","","","",UserType.ADMIN))
        private set


    fun getDetail(){
        val data = useCase.userData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    println(userdata)
                    println(result)
                    userdata = result
                },
                { error -> println("Error: ${error.message}") }
            )
    }

}