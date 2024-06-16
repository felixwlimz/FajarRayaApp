package com.fajarraya.app.utils

import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.domain.model.Suppliers

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

    val suppliers = listOf(
        Suppliers(
            supplierName = "Arashi",
            supplierAddress = "Jalan. Asia No 123 Medan",
            phone = "0851034304435",
            city = "Medan",
            province = "North Sumatra"
        ),
        Suppliers(
            supplierName = "Hannochs",
            supplierAddress = "Jalan. Jendral Sudirman No 123 Medan",
            phone = "0851034304435",
            city = "Medan",
            province = "North Sumatra"
        ),
        Suppliers(
            supplierName = "Arashi",
            supplierAddress = "Jalan. Asia No 123 Medan",
            phone = "0851034304435",
            city = "Medan",
            province = "North Sumatra"
        )
    )
}