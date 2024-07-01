package com.fajarraya.app.pages.orders.checkout

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.usecase.products.ProductUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CheckoutViewModel(private val productUseCase: ProductUseCase) : ViewModel() {

    var uploadedImage by
    mutableStateOf<Uri>(Uri.EMPTY)
    private set

    var productName by mutableStateOf("")
    private set

    fun getProduct(productId : String){
        productUseCase.getProduct(productId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
               uploadedImage = Uri.parse(it.gambarProduk)
                productName = it.namaBarang
            },
                {
                    it.printStackTrace()
                }
            )
    }

}