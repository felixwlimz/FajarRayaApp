package com.fajarraya.app.core.repository.supplier

import com.fajarraya.app.core.domain.model.Suppliers
import com.fajarraya.app.models.SortType
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface ISupplierRepository {

    fun getAllSuppliers() : Flowable<List<Suppliers>>

    fun addSupplier(supplier : Suppliers) : Completable

    fun updateSupplier(supplier : Suppliers) : Completable

    fun deleteSupplier(supplier : Suppliers) : Completable

    fun getSupplier(supplierId : String) : Flowable<Suppliers>


    fun sortSuppliers(sortType: SortType) : Flowable<List<Suppliers>>


}