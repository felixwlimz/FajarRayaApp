package com.fajarraya.app.pages.supplier

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.domain.model.Suppliers
import com.fajarraya.app.core.domain.usecase.supplier.SupplierUseCase

class SupplierViewModel(private val supplierUseCase: SupplierUseCase):ViewModel() {

    val supplierList : LiveData<List<Suppliers>> = supplierUseCase.getAllSuppliers().toLiveData()

}