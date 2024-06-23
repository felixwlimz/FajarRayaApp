package com.fajarraya.app.pages.orders.addproduct

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.domain.usecase.products.ProductUseCase
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;


class AddProductViewModel(private val productUseCase: ProductUseCase) : ViewModel() {


    fun insertProducts(products: Products) {
        viewModelScope.launch {
            productUseCase.insertProduct(products)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }
    }


}


