package com.fajarraya.app.pages.orders

import CartItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.domain.model.Suppliers
import com.fajarraya.app.core.domain.usecase.products.ProductUseCase
import com.fajarraya.app.core.domain.usecase.supplier.SupplierUseCase
import com.google.firebase.firestore.FirebaseFirestore


class OrderViewModel(productUseCase: ProductUseCase, private val firestore: FirebaseFirestore) :
    ViewModel() {


    val productList: LiveData<List<Products>> = productUseCase.getAllProducts().toLiveData()

    private val _cartItems = MutableLiveData<List<CartItem>>(emptyList())
    val cartItems: LiveData<List<CartItem>> = _cartItems

    fun addProductToCart(product: Products) {
        val currentCartItems = _cartItems.value ?: emptyList()

        val updatedCartItems = currentCartItems.toMutableList()
        val existingItemIndex =
            updatedCartItems.indexOfFirst { it.kodeBarang == product.kodeBarang }

        if (existingItemIndex >= 0) {
            val existingItem = updatedCartItems[existingItemIndex]
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + 1)
            updatedCartItems[existingItemIndex] = updatedItem
        } else {
            updatedCartItems.add(
                CartItem(
                    kodeBarang = product.kodeBarang,
                    quantity = 1,
                    gambar = product.gambarProduk,
                    harga = product.hargaProduk,
                    nama = product.namaBarang
                )
            )
        }
        _cartItems.value = updatedCartItems
    }

    fun reduceProductQuantity(product: Products) {
        val currentCartItems = _cartItems.value ?: emptyList()

        val updatedCartItems = currentCartItems.toMutableList()
        val existingItemIndex =
            updatedCartItems.indexOfFirst { it.kodeBarang == product.kodeBarang }

        if (existingItemIndex >= 0) {
            val existingItem = updatedCartItems[existingItemIndex]
            if(existingItem.quantity - 1 <= -1){
                return@reduceProductQuantity
            }
            val updatedItem = existingItem.copy(quantity = existingItem.quantity - 1)
            updatedCartItems[existingItemIndex] = updatedItem
        } else {
            updatedCartItems.add(
                CartItem(
                    kodeBarang = product.kodeBarang,
                    quantity = 0,
                    gambar = product.gambarProduk,
                    harga = product.hargaProduk,
                    nama = product.namaBarang
                )
            )
        }
        _cartItems.value = updatedCartItems
    }

}