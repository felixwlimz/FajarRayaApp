package com.fajarraya.app.core.data.local.datasource

import com.fajarraya.app.core.data.local.dao.ProductDao
import com.fajarraya.app.core.data.local.entity.ProductEntity
import io.reactivex.rxjava3.core.Flowable

class ProductDataSource(private val productDao: ProductDao){

    fun getAllProducts() : Flowable<List<ProductEntity>> = productDao.getAllProducts()

    fun getProduct(kodeBarang : String) : Flowable<ProductEntity> = productDao.getProduct(kodeBarang)

    fun insertProduct(productEntity: ProductEntity) = productDao.insertProduct(productEntity)

    fun deleteProduct(productEntity: ProductEntity) = productDao.deleteProduct(productEntity)
}