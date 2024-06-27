package com.fajarraya.app.core.repository.products

import com.fajarraya.app.core.data.local.datasource.ProductDataSource
import com.fajarraya.app.core.data.local.entity.ProductEntity
import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.utils.DataMapper
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable



class ProductRepository (private val productDataSource: ProductDataSource) : IProductRepository {


    override fun getAllProducts(): Flowable<List<Products>> {
        return productDataSource.getAllProducts().map {
            mapperDomain.mapLists(it)
        }
    }

    override fun getProduct(kodeBarang: String): Flowable<Products> {
        return productDataSource.getProduct(kodeBarang).map {
            mapperDomain.mapFrom(it)
        }
    }

    override fun insertProduct(products: Products) : Completable{
        val productMapper = mapperEntity.mapFrom(products)
        return productDataSource.insertProduct(productMapper)
    }

    override fun deleteProduct(products: Products) : Completable {
        val productMapper = mapperEntity.mapFrom(products)
        return productDataSource.deleteProduct(productMapper)
    }
    
    
    private val mapperDomain = object : DataMapper<ProductEntity, Products> {
        override fun mapFrom(data: ProductEntity): Products {
            return Products(
                kodeBarang = data.kodeBarang,
                namaBarang = data.namaBarang,
                deskripsiProduk = data.deskripsiProduk,
                stok = data.stok,
                satuan = data.satuan,
                gambarProduk = data.gambarProduk,
                supplierId = data.supplierId,
                kategoriProduk = data.kategoriProduk
            )
        }

        override fun mapLists(data: List<ProductEntity>): List<Products> {
            return data.map { data ->
                Products(
                    kodeBarang = data.kodeBarang,
                    namaBarang = data.namaBarang,
                    deskripsiProduk = data.deskripsiProduk,
                    stok = data.stok,
                    satuan = data.satuan,
                    gambarProduk = data.gambarProduk,
                    supplierId = data.supplierId,
                    kategoriProduk = data.kategoriProduk
                )
            }
        }

    }

    private val mapperEntity = object : DataMapper<Products, ProductEntity> {
        override fun mapFrom(data: Products): ProductEntity {
            return ProductEntity(
                kodeBarang = data.kodeBarang,
                namaBarang = data.namaBarang,
                deskripsiProduk = data.deskripsiProduk,
                stok = data.stok,
                satuan = data.satuan,
                gambarProduk = data.gambarProduk,
                supplierId = data.supplierId,
                kategoriProduk = data.kategoriProduk
            )
        }

        override fun mapLists(data: List<Products>): List<ProductEntity> {
            return data.map { data ->
                ProductEntity(
                    kodeBarang = data.kodeBarang,
                    namaBarang = data.namaBarang,
                    deskripsiProduk = data.deskripsiProduk,
                    stok = data.stok,
                    satuan = data.satuan,
                    gambarProduk = data.gambarProduk,
                    supplierId = data.supplierId,
                    kategoriProduk = data.kategoriProduk
                )
            }
        }

    }

}