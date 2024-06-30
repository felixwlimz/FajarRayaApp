package com.fajarraya.app.core.domain.model

import com.google.firebase.firestore.DocumentSnapshot

data class FirebaseProducts(
    val kodeBarang: String = "",
    val namaBarang: String = "",
    val stok: Int = 0,
    val satuan: String = "",
    val kategoriProduk: String = "",
    var gambarProduk: String = "",
    val deskripsiProduk: String = "",
    val supplierId: String = "",
    val hargaProduk: Long = 0L
){

    fun mapToProduct(): Products {
        return Products(
            kodeBarang = this.kodeBarang,
            namaBarang = this.namaBarang,
            stok = this.stok,
            satuan = this.satuan,
            kategoriProduk = this.kategoriProduk,
            gambarProduk = gambarProduk,
            deskripsiProduk = this.deskripsiProduk,
            supplierId = this.supplierId,
            hargaProduk = this.hargaProduk
        )
    }
}

fun DocumentSnapshot.toFirebaseProduct(): FirebaseProducts {
    return FirebaseProducts(
        kodeBarang = this.getString("kodeBarang") ?: "",
        namaBarang = this.getString("namaBarang") ?: "",
        stok = (this.getLong("stok") ?: 0L).toInt(),
        satuan = this.getString("satuan") ?: "",
        kategoriProduk = this.getString("kategoriProduk") ?: "",
        gambarProduk = this.getString("gambarProduk") ?: "",
        deskripsiProduk = this.getString("deskripsiProduk") ?: "",
        supplierId = this.getString("supplierId") ?: "",
        hargaProduk = this.getLong("hargaProduk") ?: 0L
    )
}