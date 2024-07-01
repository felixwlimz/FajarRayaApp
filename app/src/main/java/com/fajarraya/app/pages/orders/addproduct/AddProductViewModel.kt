package com.fajarraya.app.pages.orders.addproduct

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.domain.model.Suppliers
import com.fajarraya.app.core.domain.usecase.products.ProductUseCase
import com.fajarraya.app.core.domain.usecase.supplier.SupplierUseCase
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;


class AddProductViewModel(
    private val productUseCase: ProductUseCase,
    supplierUseCase: SupplierUseCase
) : ViewModel() {
    val supplierList: LiveData<List<Suppliers>> = supplierUseCase.getAllSuppliers().toLiveData()

    var uploadedImage by
    mutableStateOf<Uri>(Uri.EMPTY)

    var productName by mutableStateOf("")

    var description by mutableStateOf("")

    var price by mutableStateOf("")

    var productCategory by mutableStateOf("")

    var productDropdownExpanded by mutableStateOf(false)

    var supplier by mutableStateOf("")

    var stock by mutableStateOf("")

    var supplierDropdownExpanded by mutableStateOf(false)

    var kodebarang by mutableStateOf("")

    var satuan by mutableStateOf("")

    fun loadProductData(kodeProduk: String) {
        productUseCase.getProduct(kodeProduk)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    uploadedImage = Uri.parse(it.gambarProduk)
                    productName = it.namaBarang
                    description = it.deskripsiProduk
                    price = it.hargaProduk.toString()
                    productCategory = it.kategoriProduk
                    supplier = it.supplierId
                    stock = it.stok.toString()
                    kodebarang = it.kodeBarang
                    satuan = it.satuan
                },
                {
                    it.printStackTrace()
                }
            )
    }

    fun insertProducts(products: Products, onComplete: () -> Unit) {
        productUseCase.insertProduct(products)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onComplete()
                },
                {
                    it.printStackTrace()
                }
            )
    }

    fun editProducts(products: Products, onComplete: () -> Unit) {
        productUseCase.updateProduct(products)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    onComplete()
                },
                {
                    it.printStackTrace()
                }
            )
    }


}


