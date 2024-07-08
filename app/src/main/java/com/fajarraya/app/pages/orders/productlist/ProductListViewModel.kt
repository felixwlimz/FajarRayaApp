package com.fajarraya.app.pages.orders.productlist

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.domain.usecase.products.ProductUseCase
import com.fajarraya.app.models.SortType
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@SuppressLint("CheckResult")
class ProductListViewModel(private val productUseCase: ProductUseCase) : ViewModel() {


    private val _productList : MutableLiveData<List<Products>> = MutableLiveData()

    val productList: LiveData<List<Products>> = _productList

    fun getAllProducts(sortType : SortType){
        productUseCase.getAllProducts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                when(sortType){
                    SortType.ASCENDING -> {
                        _productList.value = it.sortedBy { product ->
                            product.namaBarang
                        }
                    }
                    SortType.DESCENDING -> {
                        _productList.value = it.sortedByDescending{ product ->
                            product.namaBarang
                        }
                    }

                }

            },
                {
                    it.printStackTrace()
                })
    }


    fun deleteProduct(products: Products, onComplete: () -> Unit) {
        productUseCase.deleteProduct(products).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                onComplete()
            }, {
                it.printStackTrace()
            })
    }

    fun searchProducts(query : String){
        productUseCase.getAllProducts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                _productList.value = it.filter { product ->
                    product.namaBarang.contains(query , ignoreCase = true)
                }
            }
    }


}