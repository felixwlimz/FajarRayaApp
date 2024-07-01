package com.fajarraya.app.pages.orders.checkout

import CartItem
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.usecase.auth.AuthUseCase
import com.fajarraya.app.core.domain.usecase.products.ProductUseCase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import toCartItem

class CheckoutViewModel(
    private val firestore: FirebaseFirestore,
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val _cartItems = MutableLiveData<List<CartItem>>(emptyList())
    val cartItems: LiveData<List<CartItem>> = _cartItems


    fun subscribeProduct() {
        getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _cartItems.value = it;
            }, {
                it.printStackTrace()
            })

    }

    fun getProducts(): Single<List<CartItem>> {
        return Single.create { emit ->

            firestore
                .collection("orders")
                .document(authUseCase.currentUser!!.uid)
                .get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        val cartItems = it.get("items") as? List<Map<String, Any>>
                        emit.onSuccess(
                            cartItems!!.map {
                                it.toCartItem()
                            }.toList(),
                        )
                    }
                }
                .addOnFailureListener {
                    emit.onError(it)
                }
        }

    }


}