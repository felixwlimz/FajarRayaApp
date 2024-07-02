package com.fajarraya.app.models

import CartItem
import com.google.firebase.firestore.DocumentSnapshot
import toCartItem

data class Transactions(
    val totalPrice : Long,
    val items : List<CartItem>,
    val date : Long,
    val status : String,
    val userid :String,
    val payment:String,
)

fun DocumentSnapshot.toTransactions(): Transactions {
    return Transactions(
        totalPrice = this["totalPrice"] as Long,
        items = (this["items"] as List<Map<String, Any>>).map{
            it.toCartItem()
        },
        date = this["date"] as Long,
        status = this["status"] as String,
        userid = this["userid"] as String,
        payment = this["payment"] as String,
    )
}