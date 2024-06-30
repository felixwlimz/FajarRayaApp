package com.fajarraya.app.core.domain.model


data class Products(
    val kodeBarang : String,
    val namaBarang : String,
    val stok : Int,
    val satuan : String,
    val kategoriProduk : String,
    var gambarProduk : String,
    val deskripsiProduk : String,
    val supplierId : String,
    val hargaProduk : Long,
){
    fun mapProductToFirebaseProduct(urlProduk:String): FirebaseProducts {
        return FirebaseProducts(
            kodeBarang = this.kodeBarang,
            namaBarang = this.namaBarang,
            stok = this.stok,
            satuan = this.satuan,
            kategoriProduk = this.kategoriProduk,
            gambarProduk = urlProduk,
            deskripsiProduk = this.deskripsiProduk,
            supplierId = this.supplierId,
            hargaProduk = this.hargaProduk
        )
    }

}

