package com.fajarraya.app.core.domain.model

data class Suppliers(
    val supplierId : String,
    val supplierName : String,
    val supplierAddress : String,
    val phoneNumber : String,
    val city : String,
    val province : String
){
    override fun toString(): String {
        return supplierName
    }
}
