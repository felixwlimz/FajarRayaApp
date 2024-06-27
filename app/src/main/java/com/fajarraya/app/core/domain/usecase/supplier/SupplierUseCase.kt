package com.fajarraya.app.core.domain.usecase.supplier

import com.fajarraya.app.core.domain.model.Suppliers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface SupplierUseCase {
    fun getAllSuppliers() : Flowable<List<Suppliers>>

    fun addSupplier(supplier : Suppliers) : Completable

    fun updateSupplier(supplier : Suppliers) : Completable

    fun deleteSupplier(supplier : Suppliers) : Completable

    fun getSupplier(supplierId : String) : Flowable<Suppliers>
}