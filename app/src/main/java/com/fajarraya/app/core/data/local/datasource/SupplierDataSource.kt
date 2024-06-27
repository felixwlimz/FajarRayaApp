package com.fajarraya.app.core.data.local.datasource

import com.fajarraya.app.core.data.local.dao.SupplierDao
import com.fajarraya.app.core.data.local.entity.SupplierEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class SupplierDataSource(private val supplierDao: SupplierDao) {


    fun getAllSuppliers() : Flowable<List<SupplierEntity>> = supplierDao.getAllSuppliers()

    fun getSupplier(supplierId : String) : Flowable<SupplierEntity> = supplierDao.getSupplier(supplierId)

    fun addSupplier(supplierEntity: SupplierEntity) : Completable = supplierDao.addSupplier(supplierEntity)


    fun updateSupplier(supplierEntity: SupplierEntity) : Completable = supplierDao.updateSupplier(supplierEntity)

    fun deleteSupplier(supplierEntity: SupplierEntity) : Completable = supplierDao.deleteSupplier(supplierEntity)

}