package com.fajarraya.app.core.domain.usecase.products

import com.fajarraya.app.core.domain.model.Products
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface ProductUseCase {

    fun getAllProducts() : Flowable<List<Products>>

    fun getProduct(kodeBarang : String) : Flowable<Products>

    fun insertProduct(products: Products) : Completable

    fun deleteProduct(products: Products) : Completable

    fun updateProduct(products: Products) : Completable

}