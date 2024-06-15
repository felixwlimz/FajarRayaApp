package com.fajarraya.app.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fajarraya.app.core.domain.usecase.ProductUseCase
import com.fajarraya.app.di.Injection
import com.fajarraya.app.pages.orders.OrderViewModel

class OrderViewModelFactory private constructor(
    private val productUseCase: ProductUseCase
) : ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var instance : OrderViewModelFactory? = null

        fun getInstance(context: Context) : OrderViewModelFactory{
            return instance ?: synchronized(this){
                instance ?: OrderViewModelFactory(
                    Injection.provideProductUseCase(context)
                )
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        return when {
            modelClass.isAssignableFrom(OrderViewModel::class.java) -> {
                OrderViewModel(productUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}