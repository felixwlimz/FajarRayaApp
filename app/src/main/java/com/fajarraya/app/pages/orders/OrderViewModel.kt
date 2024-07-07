package com.fajarraya.app.pages.orders

import CartItem
import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.domain.usecase.auth.AuthUseCase
import com.fajarraya.app.core.domain.usecase.products.ProductUseCase
import com.fajarraya.app.models.SortType
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

@SuppressLint("CheckResult")
class OrderViewModel(
    private val productUseCase: ProductUseCase,
    private val firestore: FirebaseFirestore,
    private val authUseCase: AuthUseCase,
) :
    ViewModel() {

    private val _products : MutableLiveData<List<Products>> = MutableLiveData()
    val productList: LiveData<List<Products>> = _products

    private val _cartItems = MutableLiveData<List<CartItem>>(emptyList())
    val cartItems: LiveData<List<CartItem>> = _cartItems



    fun getAllProducts(sortType: SortType) {
        productUseCase.getAllProducts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                when(sortType){
                    SortType.ASCENDING -> {
                        _products.value = it.sortedBy { product ->
                            product.namaBarang
                        }
                    }
                    SortType.DESCENDING -> {
                        _products.value = it.sortedByDescending{ product ->
                            product.namaBarang
                        }
                    }

                }
            }
    }


    fun addProductToCart(product: Products) {
        val currentCartItems = _cartItems.value ?: emptyList()

        val updatedCartItems = currentCartItems.toMutableList()
        val existingItemIndex =
            updatedCartItems.indexOfFirst { it.kodeBarang == product.kodeBarang }

        if (existingItemIndex >= 0) {
            val existingItem = updatedCartItems[existingItemIndex]
            if(existingItem.quantity + 1 > product.stok){
                return
            }
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + 1)
            updatedCartItems[existingItemIndex] = updatedItem
        } else {
            if( 1 > product.stok){
                return
            }
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
            if (existingItem.quantity - 1 <= -1) {
                return
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

    fun checkoutCart(): Completable {
        return Completable.create { emitter ->
            val uid = authUseCase.currentUser!!.uid

            firestore
                .collection("orders")
                .document(uid)
                .set(hashMapOf(
                    "items" to cartItems.value,
                ))
                .addOnSuccessListener {
                    emitter.onComplete()
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    }

    fun searchProduct(query : String?){
        productUseCase.getAllProducts()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                _products.value = it.filter { product ->
                    product.namaBarang.contains(query ?: "", ignoreCase = true)
                }
            }
    }


}