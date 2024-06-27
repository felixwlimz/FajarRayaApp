package com.fajarraya.app.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fajarraya.app.core.data.local.entity.SupplierEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface SupplierDao {

    @Query("SELECT * FROM suppliers")
    fun getAllSuppliers() : Flowable<List<SupplierEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSupplier(supplierEntity: SupplierEntity) : Completable

    @Update
    fun updateSupplier(supplierEntity: SupplierEntity) : Completable

    @Delete
    fun deleteSupplier(supplierEntity: SupplierEntity) : Completable

    @Query("SELECT * FROM suppliers WHERE supplier_id = :supplierId")
    fun getSupplier(supplierId : String) : Flowable<SupplierEntity>

}