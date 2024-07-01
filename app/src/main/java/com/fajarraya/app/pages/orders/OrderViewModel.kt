package com.fajarraya.app.pages.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.domain.model.Suppliers
import com.fajarraya.app.core.domain.usecase.products.ProductUseCase
import com.fajarraya.app.core.domain.usecase.supplier.SupplierUseCase


class OrderViewModel(productUseCase: ProductUseCase,) : ViewModel() {


   val productList : LiveData<List<Products>> = productUseCase.getAllProducts().toLiveData()


}