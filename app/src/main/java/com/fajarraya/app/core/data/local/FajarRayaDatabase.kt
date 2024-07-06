package com.fajarraya.app.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fajarraya.app.core.data.local.dao.ProductDao
import com.fajarraya.app.core.data.local.dao.SupplierDao
import com.fajarraya.app.core.data.local.entity.ProductEntity
import com.fajarraya.app.core.data.local.entity.SupplierEntity


@Database(
    entities = [ProductEntity::class, SupplierEntity::class],
    version = 2,
    exportSchema = false,
)
abstract class FajarRayaDatabase : RoomDatabase() {

    abstract fun productDao() : ProductDao

    abstract fun supplierDao() : SupplierDao


}

