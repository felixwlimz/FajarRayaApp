package com.fajarraya.app.utils

import com.fajarraya.app.core.data.local.entity.ProductEntity
import com.fajarraya.app.core.domain.model.Products

object DataMapper {

    fun mapEntitiesToDomain(productEntities : List<ProductEntity>) : List<Products>{
        return productEntities.map{ productEntity ->
            Products(
                kodeBarang = productEntity.kodeBarang,
                namaBarang = productEntity.namaBarang,
                hargaJual = productEntity.hargaJual,
                stokAwal = productEntity.stokAwal,
                stokMasuk = productEntity.stokMasuk,
                stokAkhir = productEntity.stokAkhir,
                stokKeluar = productEntity.stokKeluar
            )
        }
    }

    fun mapEntitiyToDomain(productEntity : ProductEntity) : Products{
        return Products(
            kodeBarang = productEntity.kodeBarang,
            namaBarang = productEntity.namaBarang,
            hargaJual = productEntity.hargaJual,
            stokAwal = productEntity.stokAwal,
            stokMasuk = productEntity.stokMasuk,
            stokAkhir = productEntity.stokAkhir,
            stokKeluar = productEntity.stokKeluar
        )
    }

    fun mapDomainToEntities(products : List<Products>) : List<ProductEntity>{
        return products.map{ productEntity ->
            ProductEntity(
                kodeBarang = productEntity.kodeBarang,
                namaBarang = productEntity.namaBarang,
                hargaJual = productEntity.hargaJual,
                stokAwal = productEntity.stokAwal,
                stokMasuk = productEntity.stokMasuk,
                stokAkhir = productEntity.stokAkhir,
                stokKeluar = productEntity.stokKeluar
            )
        }
    }

    fun mapDomainToEntity(product: Products) : ProductEntity{
        return ProductEntity(
            kodeBarang = product.kodeBarang,
            namaBarang = product.namaBarang,
            hargaJual = product.hargaJual,
            stokAwal = product.stokAwal,
            stokMasuk = product.stokMasuk,
            stokAkhir = product.stokAkhir,
            stokKeluar = product.stokKeluar
        )
    }
}