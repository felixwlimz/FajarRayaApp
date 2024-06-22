package com.fajarraya.app.core.domain.model

data class Transactions(
    val purchaseItem : String,
    val quantity : Int,
    val price : Int,
    val isCompleted : Boolean
)
