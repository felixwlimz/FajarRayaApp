package com.fajarraya.app.utils

import com.fajarraya.app.core.domain.model.Products
import com.fajarraya.app.core.domain.model.Suppliers
import com.fajarraya.app.core.domain.model.Transactions

object DummyData {

    val products = listOf(
        Products(
            kodeBarang = "A-100",
            namaBarang = "Arashi Bola Lampu",
            hargaProduk = 100000,
            stok = 100,
            satuan = "Pcs",
            deskripsiProduk = "",
            gambarProduk = ByteArray(0),
            supplierId = "NYR-01",
            kategoriProduk = "Rice Cooker",
        ),
        Products(
            kodeBarang = "A-101",
            namaBarang = "Kipas Angin Miyako",
            hargaProduk = 500000,
            stok = 100,
            satuan = "Pcs",
            deskripsiProduk = "",
            gambarProduk = ByteArray(0),
            supplierId = "NYR-01",
            kategoriProduk = "Rice Cooker",
        ),
        Products(
            kodeBarang = "A-102",
            namaBarang = "Blender Electrolux",
            hargaProduk = 2000000,
            stok = 100,
            satuan = "Pcs",
            deskripsiProduk = "",
            gambarProduk = ByteArray(0),
            supplierId = "NYR-01",
            kategoriProduk = "Rice Cooker",
        )
    )

    val suppliers = listOf(
        Suppliers(
            supplierName = "Arashi",
            supplierAddress = "Jalan. Asia No 123 Medan",
            phoneNumber = "0851034304435",
            city = "Medan",
            province = "North Sumatra",
            supplierId = "NYR-01"
        ),
        Suppliers(
            supplierName = "Hannochs",
            supplierAddress = "Jalan. Jendral Sudirman No 123 Medan",
            phoneNumber = "0851034304435",
            city = "Medan",
            province = "North Sumatra",
            supplierId = "NYR-02"
        ),
        Suppliers(
            supplierName = "Arashi",
            supplierAddress = "Jalan. Asia No 123 Medan",
            phoneNumber = "0851034304435",
            city = "Medan",
            province = "North Sumatra",
            supplierId = "NYR-03"
        )
    )

    val categories = arrayListOf(
        "Rice Cooker",
        "Seterika",
        "Hand Mixer",
        "Kipas Angin",
        "Dispenser",
        "Selang Regulator",
        "Fry Pan",
        "Kepala Regulator",
        ""
    )

    val transactions = listOf(
        Transactions(
            purchaseItem = "Arashi Bola Lampu",
            quantity = 3,
            price = 50000,
            isCompleted = true
        ),
        Transactions(
            purchaseItem = "Kipas Angin Miyako",
            quantity = 1,
            price = 250000,
            isCompleted = true
        ),
        Transactions(
            purchaseItem = "Oxone Oven",
            quantity = 2,
            price = 1000000,
            isCompleted = false
        )
    )

}