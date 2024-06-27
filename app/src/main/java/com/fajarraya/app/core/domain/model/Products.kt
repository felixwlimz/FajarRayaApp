package com.fajarraya.app.core.domain.model


data class Products(
    val kodeBarang : String,
    val namaBarang : String,
    val stok : Int,
    val satuan : String,
    val kategoriProduk : String,
    val gambarProduk : ByteArray,
    val deskripsiProduk : String,
    val supplierId : String
)

