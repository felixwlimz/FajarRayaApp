package com.fajarraya.app.core.repository

import com.fajarraya.app.core.data.local.LocalDataSource
import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.utils.DataMapper
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable



class ProductRepository (private val localDataSource: LocalDataSource) : IProductRepository {


    override fun getAllProducts(): Flowable<List<Products>> {
        return localDataSource.getAllProducts().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getProduct(kodeBarang: String): Flowable<Products> {
        return localDataSource.getProduct(kodeBarang).map {
            DataMapper.mapEntitiyToDomain(it)
        }
    }

    override fun insertProduct(products: Products) : Completable{
        val productMapper = DataMapper.mapDomainToEntity(products)
        return localDataSource.insertProduct(productMapper)
    }

    override fun deleteProduct(products: Products) : Completable {
        val productMapper = DataMapper.mapDomainToEntity(products)
        return localDataSource.deleteProduct(productMapper)
    }

}