package com.fajarraya.app.pages.supplier

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.model.Suppliers
import com.fajarraya.app.core.domain.usecase.supplier.SupplierUseCase
import com.fajarraya.app.models.SortType
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@SuppressLint("CheckResult")
class SupplierViewModel(private val supplierUseCase: SupplierUseCase) : ViewModel() {


    private val _suppliers : MutableLiveData<List<Suppliers>> = MutableLiveData()
    val suppliers : LiveData<List<Suppliers>> = _suppliers


    fun getAllSuppliers(sortType: SortType){
        supplierUseCase.getAllSuppliers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(sortType){
                    SortType.ASCENDING -> {
                        _suppliers.value = it.sortedBy { supplier ->
                            supplier.supplierName
                        }
                    }
                    SortType.DESCENDING -> {
                        _suppliers.value = it.sortedByDescending { supplier ->
                            supplier.supplierName
                        }
                    }
                }
            }, {
                it.printStackTrace()
            })

    }

    fun searchSupplier(query : String){
        supplierUseCase.getAllSuppliers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _suppliers.value = it.filter { supplier ->
                    supplier.supplierName.contains(query, ignoreCase = true)
                }
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