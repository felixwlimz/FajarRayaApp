package com.fajarraya.app.pages.transactions

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fajarraya.app.core.domain.usecase.auth.AuthUseCase
import com.fajarraya.app.models.Transactions
import com.fajarraya.app.models.UserType
import com.fajarraya.app.models.toTransactions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class TransactionsViewModel(
    private val firestore: FirebaseFirestore,
    val authUseCase: AuthUseCase,
) : ViewModel() {

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
            var f : Query = firestore
                .collection("transactions")

            authUseCase.userData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { e ->

                    if (e.superAdmin == UserType.EMPLOYEE) {
                        f = f.whereEqualTo("userid", authUseCase.currentUser!!.uid)
                    }

                    f.get()
                        .addOnSuccessListener {
                            if (!it.isEmpty) {
                                val z = mutableListOf<Transactions>()
                                for (document in it.documents) {
                                    z.add(document.toTransactions(document.id))
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


}