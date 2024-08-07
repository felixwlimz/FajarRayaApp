package com.fajarraya.app.pages.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.model.toFirebaseProduct
import com.fajarraya.app.models.toTransactions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.Date

class HomeViewModel(private val firebaseFirestore: FirebaseFirestore) : ViewModel() {

    val productsRef = firebaseFirestore.collection("products")
    val transactionsRef = firebaseFirestore.collection("transactions")

    var productSize by mutableStateOf(0)
    var outOfStock by mutableStateOf(0)

    var salesSize by mutableStateOf(0)
    var salesTotal by   mutableStateOf(0L)

    var todayRevenue by mutableStateOf(0L)

    val todayDate = SimpleDateFormat("MM/dd/yyyy").format(Date())

    fun getProductUpdates(): Observable<Int> {
        return Observable.create { emitter ->
            val listenerRegistration: ListenerRegistration =
                productsRef.addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        emitter.onError(e)
                        return@addSnapshotListener
                    }
                    if (snapshot != null && !snapshot.isEmpty) {
                        var outofstock = 0
                        for (doc in snapshot.documents) {
                            val fbsProduct = doc.toFirebaseProduct()
                            if (fbsProduct.stok == 0) {
                                outofstock++
                            }
                        }
                        productSize = snapshot.documents.size
                        outOfStock = outofstock
                        emitter.onNext(snapshot.documents.size)
                    }
                }
            emitter.setCancellable { listenerRegistration.remove() }
        }
    }


    fun getTransactionUpdates() : Observable<Int> {
        return Observable.create { emitter ->
            val listenerRegistration: ListenerRegistration =
                transactionsRef.addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        emitter.onError(e)
                        return@addSnapshotListener
                    }
                    if (snapshot != null && !snapshot.isEmpty) {

                        var totalRevenue = 0L
                        var totalSalesToday = 0L
                        for (document in snapshot.documents) {
                            val trans = document.toTransactions(document.id)
                            totalRevenue += trans.totalPrice

                            val transDate = SimpleDateFormat("MM/dd/yyyy").format(Date(trans.date))

                            if(transDate == todayDate){
                                totalSalesToday+= trans.totalPrice
                            }
                        }
                        salesTotal = totalRevenue
                        salesSize = snapshot.documents.size
                        todayRevenue = totalSalesToday

                        emitter.onNext(snapshot.documents.size)
                    }
                }
            emitter.setCancellable { listenerRegistration.remove() }
        }
    }





    fun listenTransactionUpdates() {
        getTransactionUpdates()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }


    fun listenProductUpdates() {
        getProductUpdates()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }


}