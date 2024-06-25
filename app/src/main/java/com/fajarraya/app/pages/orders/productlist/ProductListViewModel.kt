package com.fajarraya.app.pages.orders.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.domain.usecase.products.ProductUseCase

class ProductListViewModel(productUseCase: ProductUseCase) : ViewModel() {

    val productList : LiveData<List<Products>> = productUseCase.getAllProducts().toLiveData()

}