package com.fajarraya.app.core.domain.usecase.supplier

import com.fajarraya.app.core.domain.model.Suppliers
import com.fajarraya.app.core.repository.supplier.ISupplierRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

class SupplierInteractor(private val supplierRepository: ISupplierRepository) : SupplierUseCase {
    override fun getAllSuppliers(): Flowable<List<Suppliers>> {
        return supplierRepository.getAllSuppliers()
    }

    override fun addSupplier(supplier: Suppliers): Completable {
        return supplierRepository.addSupplier(supplier)
    }

    override fun updateSupplier(supplier: Suppliers): Completable {
        return supplierRepository.updateSupplier(supplier)
    }

    override fun deleteSupplier(supplier: Suppliers): Completable {
        return supplierRepository.deleteSupplier(supplier)
    }

    override fun getSupplier(supplierId: String): Flowable<Suppliers> {
        return supplierRepository.getSupplier(supplierId)
    }
}