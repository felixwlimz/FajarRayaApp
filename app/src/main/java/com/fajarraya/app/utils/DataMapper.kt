package com.fajarraya.app.utils

import com.fajarraya.app.core.data.local.entity.ProductEntity
import com.fajarraya.app.core.data.remote.UserResponse
import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.domain.model.User

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

    fun mapUserResponseToDomain(userResponse: UserResponse) : User =
        User(
            userId = userResponse.userId,
            name = userResponse.email,
            username = userResponse.username,
            password = userResponse.password,
            email = userResponse.email,
            superAdmin = userResponse.superAdmin
        )

    fun mapUserDomainToResponse(user : User) : UserResponse =
        UserResponse(
            userId = user.userId,
            name = user.email,
            username = user.username,
            password = user.password,
            email = user.email,
            superAdmin = user.superAdmin
        )
}