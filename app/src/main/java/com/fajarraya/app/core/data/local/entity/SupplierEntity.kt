package com.fajarraya.app.core.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fajarraya.app.core.domain.model.FirebaseProducts
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "suppliers")
data class SupplierEntity(

    @PrimaryKey
    @ColumnInfo("supplier_id", index = true)
    val supplierId: String,

    @ColumnInfo("supplier_name")
    val supplierName: String,

    @ColumnInfo("supplier_address")
    val supplierAddress: String,

    @ColumnInfo("phone_number")
    val phoneNumber: String,

    @ColumnInfo("city")
    val city: String,

    @ColumnInfo("province")
    val province: String
) : Parcelable

fun DocumentSnapshot.toSupplierEntity(): SupplierEntity {
    return SupplierEntity(
        supplierId = this.getString("supplierId") ?: "",
        supplierName = this.getString("supplierName") ?: "",
        supplierAddress = this.getString("supplierAddress") ?: "",
        phoneNumber = this.getString("phoneNumber") ?: "",
        city = this.getString("city") ?: "",
        province = this.getString("province") ?: "",
    )
}