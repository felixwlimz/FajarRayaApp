package com.fajarraya.app.pages.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.usecase.auth.AuthUseCase
import com.fajarraya.app.models.Transactions
import com.fajarraya.app.models.toTransactions
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class TransactionsViewModel(
    private val firestore: FirebaseFirestore,
    private val authUseCase: AuthUseCase,
): ViewModel() {

    private val _transactions = MutableLiveData<List<Transactions>>(emptyList())
    val transactions: LiveData<List<Transactions>> = _transactions


    fun subscribeTransactions() {
        getTransactions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _transactions.value = it
            }, {
                it.printStackTrace()
            })

    }
    fun getTransactions(): Single<List<Transactions>> {
        return Single.create { emit ->
            firestore
                .collection("transactions")
                .whereEqualTo("userid", authUseCase.currentUser!!.uid)
                .get()
                .addOnSuccessListener {
                    if (!it.isEmpty) {
                        val z = mutableListOf<Transactions>()
                        for (document in it.documents) {
                            z.add(document.toTransactions())
                        }
                        emit.onSuccess(z)
                    } else {
                        emit.onError(Exception("Error Querying Document"))
                    }
                }
                .addOnFailureListener {
                    emit.onError(it)
                }
        }

    }




}