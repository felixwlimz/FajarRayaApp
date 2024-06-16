package com.fajarraya.app.di

import com.fajarraya.app.core.domain.usecase.ProductInteractor
import com.fajarraya.app.core.domain.usecase.ProductUseCase
import com.fajarraya.app.pages.orders.OrderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ProductUseCase>{ ProductInteractor(get()) }
}

val viewModelModule = module {
    viewModel{
        OrderViewModel(get())
    }
}