package com.fajarraya.app.pages.supplier.addeditsupplier


import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.model.Suppliers
import com.fajarraya.app.core.domain.usecase.supplier.SupplierUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AddEditSupplierViewModel(private val supplierUseCase: SupplierUseCase) : ViewModel() {


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