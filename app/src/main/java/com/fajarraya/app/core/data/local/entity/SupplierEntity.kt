package com.fajarraya.app.core.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "suppliers")
data class SupplierEntity(

    @PrimaryKey
    @ColumnInfo("supplier_id")
    val supplierId : String,

    @ColumnInfo("supplier_name")
    val supplierName : String,

    @ColumnInfo("supplier_address")
    val supplierAddress : String,

    @ColumnInfo("phone_number")
    val phoneNumber : String,

    @ColumnInfo("city")
    val city : String,

    @ColumnInfo("province")
    val province : String
) : Parcelable