package com.fajarraya.app.pages.orders.checkout

import CartItem
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.domain.usecase.auth.AuthUseCase
import com.fajarraya.app.core.domain.usecase.products.ProductUseCase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import toCartItem

class CheckoutViewModel(
    private val firestore: FirebaseFirestore,
    private val authUseCase: AuthUseCase,
    private val productsUseCase: ProductUseCase
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

    fun removeStokAsQuantity(kodeBarang:String, quantity: Int): Completable {
        return Completable.create { emitter ->

            productsUseCase.getProduct(kodeBarang)
                .subscribe{prod->

                    firestore
                        .collection("products")
                        .whereEqualTo("kodeBarang", kodeBarang)
                        .get()
                        .addOnSuccessListener {
                            if (!it.isEmpty) {

                                val firebaseProducts = prod.mapProductToFirebaseProduct().apply{
                                    this.stok -= quantity
                                }
                                it.documents[0].reference.set(firebaseProducts)
                                    .addOnSuccessListener {
                                        emitter.onComplete()
                                    }
                                    .addOnFailureListener {
                                        emitter.onError(Exception(it))
                                    }
                            } else {
                                emitter.onError(Exception("Error Querying Document"))
                            }
                        }
                        .addOnFailureListener {
                            emitter.onError(Exception(it))
                        }
                }

        }
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

    fun addTransaction(payment:String,totalPrice: Long): Single<String> {
        return Single.create { emit ->

            for (cartItem in cartItems.value!!) {
                removeStokAsQuantity(cartItem.kodeBarang, cartItem.quantity)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

            firestore
                .collection("transactions")
                .add(
                    hashMapOf(
                        "payment" to payment,
                        "totalPrice" to totalPrice,
                        "items" to cartItems.value,
                        "date" to System.currentTimeMillis(),
                        "status" to "Completed",
                        "userid" to authUseCase.currentUser!!.uid
                    )
                )
                .addOnSuccessListener {
                    emit.onSuccess(it.id)
                }
                .addOnFailureListener {
                    emit.onError(it)
                }
        }
    }


}