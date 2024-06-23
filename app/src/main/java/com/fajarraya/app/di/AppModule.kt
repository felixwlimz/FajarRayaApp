package com.fajarraya.app.di

import com.fajarraya.app.core.domain.usecase.auth.AuthInteractor
import com.fajarraya.app.core.domain.usecase.auth.AuthUseCase
import com.fajarraya.app.core.domain.usecase.products.ProductInteractor
import com.fajarraya.app.core.domain.usecase.products.ProductUseCase
import com.fajarraya.app.pages.auth.login.LoginViewModel
import com.fajarraya.app.pages.auth.register.RegisterViewModel
import com.fajarraya.app.pages.orders.OrderViewModel
import com.fajarraya.app.pages.orders.addproduct.AddProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ProductUseCase>{ ProductInteractor(get()) }
    factory<AuthUseCase>{ AuthInteractor(get())}
}

val viewModelModule = module {
    viewModel{
        OrderViewModel(get())
    }
    viewModel{
        AddProductViewModel(get())
    }

    viewModel {
        LoginViewModel(get())
    }

    viewModel {
        RegisterViewModel(get())
    }

}