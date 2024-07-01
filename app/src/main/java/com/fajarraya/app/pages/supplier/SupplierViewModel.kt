package com.fajarraya.app.pages.supplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import co.touchlab.stately.concurrency.synchronize
import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.domain.model.Suppliers
import com.fajarraya.app.core.domain.usecase.supplier.SupplierUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SupplierViewModel(private val supplierUseCase: SupplierUseCase) : ViewModel() {


    var supplierList by mutableStateOf(listOf<Suppliers>())

    fun updateSupplier(){
        supplierUseCase.getAllSuppliers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                supplierList = it
            }, {
                it.printStackTrace()
            })
    }
    fun deleteSupplier(it: Suppliers) {
        supplierUseCase.deleteSupplier(it)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {
                it.printStackTrace()
            })

    }

}