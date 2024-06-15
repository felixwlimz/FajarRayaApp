package com.fajarraya.app.core.domain.usecase

import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.repository.IProductRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class ProductInteractor(private val productRepository: IProductRepository) : ProductUseCase {

    override fun getAllProducts(): Flowable<List<Products>> {
        return productRepository.getAllProducts()
    }

    override fun getProduct(kodeBarang: String): Flowable<Products> {
        return productRepository.getProduct(kodeBarang)
    }

    override fun insertProduct(products: Products) : Completable {
        return productRepository.insertProduct(products)
    }

    override fun deleteProduct(products: Products) : Completable {
        return productRepository.deleteProduct(products)
    }


}