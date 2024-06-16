package com.fajarraya.app.core.data.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fajarraya.app.core.data.local.entity.ProductEntity


@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class FajarRayaDatabase : RoomDatabase() {

    abstract fun productDao() : FajarRayaDao


}

