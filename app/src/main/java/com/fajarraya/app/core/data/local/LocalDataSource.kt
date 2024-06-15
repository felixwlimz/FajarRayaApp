package com.fajarraya.app.core.data.local

import com.fajarraya.app.core.data.local.dao.FajarRayaDao
import com.fajarraya.app.core.data.local.entity.ProductEntity
import io.reactivex.rxjava3.core.Flowable

class LocalDataSource private constructor (private val productDao: FajarRayaDao){

     fun getAllProducts() : Flowable<List<ProductEntity>> = productDao.getAllProducts()

     fun getProduct(kodeBarang : String) : Flowable<ProductEntity> = productDao.getProduct(kodeBarang)

     fun insertProduct(productEntity: ProductEntity) = productDao.insertProduct(productEntity)

     fun deleteProduct(productEntity: ProductEntity) = productDao.deleteProduct(productEntity)

     companion object{
          @Volatile
          private var instance: LocalDataSource? = null

          fun getInstance(dao : FajarRayaDao) : LocalDataSource {
               return instance ?: synchronized(this){
                    instance ?: LocalDataSource(dao)
               }.also { instance = it }
          }
     }
}