package com.fajarraya.app.core.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "fajar_raya")
@Parcelize
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "Kode_Barang")
    val kodeBarang : String,

    @ColumnInfo(name = "Nama_Barang")
    val namaBarang : String,

    @ColumnInfo(name = "Harga_Jual")
    val hargaJual : Int,

    @ColumnInfo(name = "Stok_Awal")
    val stokAwal : Int,

    @ColumnInfo(name = "Stok_Masuk")
    val stokMasuk : Int,

    @ColumnInfo(name = "Stok_Keluar")
    val stokKeluar : Int,

    @ColumnInfo(name = "Stok_Akhir")
    val stokAkhir : Int,
) : Parcelable
