package com.fajarraya.app.core.domain.usecase.products

import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.repository.products.IProductRepository
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

    override fun updateProduct(products: Products): Completable {
        return productRepository.updateProduct(products)
    }


}