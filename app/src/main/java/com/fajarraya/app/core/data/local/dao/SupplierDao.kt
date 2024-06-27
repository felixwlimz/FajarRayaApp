package com.fajarraya.app.core.data.local.dao

import com.fajarraya.app.core.data.local.entity.SupplierEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface SupplierDao {

    fun getAllSuppliers() : Flowable<List<SupplierEntity>>

    fun addSupplier(supplierEntity: SupplierEntity) : Completable

    fun updateSupplier(supplierEntity: SupplierEntity) : Completable

    fun deleteSupplier(supplierEntity: SupplierEntity) : Completable

    fun getSupplier(supplierId : String) : Flowable<SupplierEntity>

}