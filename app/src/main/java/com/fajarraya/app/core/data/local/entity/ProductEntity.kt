package com.fajarraya.app.core.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "products", foreignKeys = [
    ForeignKey(
        entity = SupplierEntity::class,
        childColumns = ["supplier_id"],
        parentColumns = ["kode_barang"]
    )
])
@Parcelize
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "kode_barang")
    val kodeBarang : String,

    @ColumnInfo(name = "kode_barang")
    val namaBarang : String,

    @ColumnInfo(name = "stok")
    val stok : Int,

    @ColumnInfo(name = "satuan")
    val satuan : String,

    @ColumnInfo(name = "kategori")
    val kategoriProduk : String,

    @ColumnInfo(name = "gambar_produk")
    val gambarProduk : ByteArray,

    @ColumnInfo(name = "deskripsi_produk")
    val deskripsiProduk : String,

    @ColumnInfo(name ="supplier_id")
    val supplierId : String
) : Parcelable
