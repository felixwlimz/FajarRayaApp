package com.fajarraya.app.core.repository.supplier

import com.fajarraya.app.core.data.local.datasource.SupplierDataSource
import com.fajarraya.app.core.data.local.entity.SupplierEntity
import com.fajarraya.app.core.domain.model.Suppliers
import com.fajarraya.app.core.utils.DataMapper
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class SupplierRepository(private val supplierDataSource: SupplierDataSource) : ISupplierRepository {

    override fun getAllSuppliers(): Flowable<List<Suppliers>> {
        return supplierDataSource.getAllSuppliers().map {
            mapperToDomain.mapLists(it)
        }
    }

    override fun addSupplier(supplier: Suppliers): Completable {
        return supplierDataSource.addSupplier(mapperToEntity.mapFrom(supplier))
    }

    override fun updateSupplier(supplier: Suppliers): Completable {
        return supplierDataSource.addSupplier(mapperToEntity.mapFrom(supplier))
    }

    override fun deleteSupplier(supplier: Suppliers): Completable {
        return supplierDataSource.deleteSupplier(mapperToEntity.mapFrom(supplier))
    }

    override fun getSupplier(supplierId: String): Flowable<Suppliers> {
        return supplierDataSource.getSupplier(supplierId).map {
            mapperToDomain.mapFrom(it)
        }
    }

    private val mapperToDomain = object : DataMapper< SupplierEntity ,Suppliers> {
        override fun mapFrom(data: SupplierEntity): Suppliers {
            return Suppliers(
                supplierId = data.supplierId,
                supplierName = data.supplierName,
                supplierAddress = data.supplierAddress,
                phoneNumber = data.phoneNumber,
                city = data.city,
                province = data.province
            )
        }

        override fun mapLists(data: List<SupplierEntity>): List<Suppliers> {
            return data.map {
                Suppliers(
                    supplierId = it.supplierId,
                    supplierName = it.supplierName,
                    supplierAddress = it.supplierAddress,
                    phoneNumber = it.phoneNumber,
                    city = it.city,
                    province = it.province
                )
            }
        }

    }

    private val mapperToEntity = object : DataMapper< Suppliers ,SupplierEntity> {
        override fun mapFrom(data: Suppliers): SupplierEntity {
            return SupplierEntity(
                supplierId = data.supplierId,
                supplierName = data.supplierName,
                supplierAddress = data.supplierAddress,
                phoneNumber = data.phoneNumber,
                city = data.city,
                province = data.province
            )
        }

        override fun mapLists(data: List<Suppliers>): List<SupplierEntity> {
            return data.map {
                SupplierEntity(
                    supplierId = it.supplierId,
                    supplierName = it.supplierName,
                    supplierAddress = it.supplierAddress,
                    phoneNumber = it.phoneNumber,
                    city = it.city,
                    province = it.province
                )
            }
        }

    }
}