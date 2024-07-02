package com.fajarraya.app.di

import com.fajarraya.app.core.domain.usecase.auth.AuthInteractor
import com.fajarraya.app.core.domain.usecase.auth.AuthUseCase
import com.fajarraya.app.core.domain.usecase.datastore.PrefInteractor
import com.fajarraya.app.core.domain.usecase.datastore.PrefUseCase
import com.fajarraya.app.core.domain.usecase.products.ProductInteractor
import com.fajarraya.app.core.domain.usecase.products.ProductUseCase
import com.fajarraya.app.core.domain.usecase.supplier.SupplierInteractor
import com.fajarraya.app.core.domain.usecase.supplier.SupplierUseCase
import com.fajarraya.app.pages.auth.login.LoginViewModel
import com.fajarraya.app.pages.auth.register.RegisterViewModel
import com.fajarraya.app.pages.home.HomeViewModel
import com.fajarraya.app.pages.orders.OrderViewModel
import com.fajarraya.app.pages.orders.addproduct.AddProductViewModel
import com.fajarraya.app.pages.orders.checkout.CheckoutViewModel
import com.fajarraya.app.pages.orders.productlist.ProductListViewModel
import com.fajarraya.app.pages.printreceipt.PrintReceiptViewModel
import com.fajarraya.app.pages.profile.ProfilePageViewModel
import com.fajarraya.app.pages.profile.detail.ProfileDetailViewModel
import com.fajarraya.app.pages.splash.SplashViewModel
import com.fajarraya.app.pages.supplier.SupplierViewModel
import com.fajarraya.app.pages.supplier.addeditsupplier.AddEditSupplierViewModel
import com.fajarraya.app.pages.transactions.TransactionsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<ProductUseCase>{ ProductInteractor(get()) }
    factory<AuthUseCase>{ AuthInteractor(get())}
    factory<PrefUseCase> { PrefInteractor(get())  }
    factory<SupplierUseCase> { SupplierInteractor(get()) }
}

val viewModelModule = module {
    viewModel{
        SplashViewModel(get())
    }

    viewModel{
        OrderViewModel(get(),get(),get())
    }
    viewModel{
        AddProductViewModel(get(),get())
    }

    viewModel {
        LoginViewModel(get())
    }

    viewModel {
        RegisterViewModel(get())
    }

    viewModel{
        ProductListViewModel(get())
    }

    viewModel {
        ProfilePageViewModel(get())
    }
    viewModel{
        ProfileDetailViewModel(get())
    }

    viewModel {
        AddEditSupplierViewModel(get())
    }

    viewModel {
        CheckoutViewModel(get(),get(),get())
    }

    viewModel{
        SupplierViewModel(get())
    }

    viewModel{
        HomeViewModel(get())
    }

    viewModel{
        TransactionsViewModel(get(),get(),)
    }

    viewModel {
        PrintReceiptViewModel(get(),)
    }

}