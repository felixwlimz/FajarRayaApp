package com.fajarraya.app.pages.printreceipt

import CartItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fajarraya.app.models.Transactions
import com.fajarraya.app.models.toTransactions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class PrintReceiptViewModel(
    private val firebaseFirestore: FirebaseFirestore,
) : ViewModel() {

    private val _totalitem = MutableLiveData<Int>(0)
    val totalitem: LiveData<Int> = _totalitem

    private val _totalpricewotax = MutableLiveData<Long>(0L)
    val totalpricewotax: LiveData<Long> = _totalpricewotax

    private val _tax = MutableLiveData<Long>(0L)
    val tax: LiveData<Long> = _tax

    private val _totalprice = MutableLiveData<Long>(0L)
    val totalprice: LiveData<Long> = _totalprice

    private val _payment = MutableLiveData<String>("")
    val payment : LiveData<String> = _payment

    private val _transaction = MutableLiveData<Transactions>()
    val transaction: LiveData<Transactions> = _transaction


    fun loadItemData(transactionID: String){
        getItemsData(transactionID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    _transaction.value = it

                    var qty = 0
                    var price = 0L
                    it.items.forEach { item ->
                        qty += item.quantity
                        price += item.quantity*item.harga
                    }
                    _totalitem.value = qty
                    _totalpricewotax.value = price
                    _tax.value = price * 10/100
                    _totalprice.value = price + (price*10/100)

                    _payment.value = it.payment
                },
                {
                    it.printStackTrace()
                }
            )
    }

    fun getItemsData(transactionID: String): Single<Transactions> {
        return Single.create { emitter ->
            firebaseFirestore
                .collection("transactions")
                .document(transactionID)
                .get()
                .addOnSuccessListener {
                    val transactions = it.toTransactions()
                    emitter.onSuccess(transactions)
                }
                .addOnFailureListener {
                    emitter.onError(it)
                }
        }
    }


}