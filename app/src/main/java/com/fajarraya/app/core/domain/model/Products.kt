package com.fajarraya.app.core.domain.model


data class Products(
    val kodeBarang : String,
    val namaBarang : String,
    val hargaJual : Int,
    val stokAwal : Int,
    val stokMasuk : Int,
    val stokKeluar : Int,
    val stokAkhir : Int,
)

