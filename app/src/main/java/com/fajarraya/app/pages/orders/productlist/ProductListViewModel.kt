package com.fajarraya.app.pages.orders.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.domain.usecase.products.ProductUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ProductListViewModel(val productUseCase: ProductUseCase) : ViewModel() {

    val productList : LiveData<List<Products>> = productUseCase.getAllProducts().toLiveData()


    fun deleteProduct(products: Products,onComplete: () -> Unit){
        productUseCase.deleteProduct(products)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onComplete()
                },
                {
                    it.printStackTrace()
                }
            )
    }


}