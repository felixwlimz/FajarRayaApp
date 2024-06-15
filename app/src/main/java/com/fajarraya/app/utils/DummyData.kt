package com.fajarraya.app.utils

import com.fajarraya.app.core.domain.model.Products

object DummyData {

    val products = listOf(
        Products(
            kodeBarang = "A-100",
            namaBarang = "Arashi Bola Lampu",
            hargaJual = 100000,
            stokAwal = 100,
            stokMasuk = 20,
            stokKeluar = 0,
            stokAkhir = 120
        ),
        Products(
            kodeBarang = "A-101",
            namaBarang = "Kipas Angin Miyako",
            hargaJual = 500000,
            stokAwal = 80,
            stokMasuk = 20,
            stokKeluar = 0,
            stokAkhir = 100
        ),
        Products(
            kodeBarang = "A-102",
            namaBarang = "Blender Electrolux",
            hargaJual = 2000000,
            stokAwal = 50,
            stokMasuk = 10,
            stokKeluar = 0,
            stokAkhir = 60
        )

    )
}