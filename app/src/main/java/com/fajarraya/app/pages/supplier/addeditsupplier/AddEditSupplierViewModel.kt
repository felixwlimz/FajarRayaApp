package com.fajarraya.app.pages.supplier.addeditsupplier


import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.model.Suppliers
import com.fajarraya.app.core.domain.usecase.supplier.SupplierUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AddEditSupplierViewModel(
    private val supplierUseCase: SupplierUseCase,
) : ViewModel() {

    var supplierId by
        mutableStateOf("")

    var supplierName by
        mutableStateOf("")


    var supplierAddress by
        mutableStateOf("")


    var description by
        mutableStateOf("")

    var phoneNumber by
        mutableStateOf("")


    var city by
        mutableStateOf("")


    var supplierProvince by
        mutableStateOf("")

    fun loadSupplierData(supplierID: String) {
        supplierUseCase.getSupplier(supplierID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    supplierId = it.supplierId
                    supplierName = it.supplierName
                    supplierAddress = it.supplierAddress
                    description = it.description
                    phoneNumber = it.phoneNumber
                    city = it.city
                }
            )
    }

    fun updateSupplier(
        suppliers: Suppliers,
        onCompleted: () -> Unit,
    ) {
        supplierUseCase.updateSupplier(suppliers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onCompleted();
                }
            )
    }

    fun addSupplier(
        suppliers: Suppliers,
        onCompleted: () -> Unit,
    ) {
        supplierUseCase.addSupplier(suppliers)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onCompleted();
                }
            )
    }


}