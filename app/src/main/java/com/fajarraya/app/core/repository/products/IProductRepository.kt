package com.fajarraya.app.core.repository.products

import com.fajarraya.app.core.domain.model.Products
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

interface IProductRepository {

    fun getAllProducts() : Flowable<List<Products>>

    fun getProduct(kodeBarang : String) : Flowable<Products>

    fun insertProduct(products: Products) : Completable

    fun deleteProduct(products: Products) : Completable


}