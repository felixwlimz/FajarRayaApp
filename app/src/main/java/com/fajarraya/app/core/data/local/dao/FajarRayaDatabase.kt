package com.fajarraya.app.core.data.local.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fajarraya.app.core.data.local.entity.ProductEntity


@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class FajarRayaDatabase : RoomDatabase() {

    abstract fun productDao() : FajarRayaDao

    companion object{
        @Volatile
        private var INSTANCE : FajarRayaDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context) : FajarRayaDatabase{
            if(INSTANCE == null){
                synchronized(FajarRayaDatabase::class){
                    INSTANCE = Room.databaseBuilder( context.applicationContext, FajarRayaDatabase::class.java, "fajar_raya")
                        .createFromAsset("fajar_raya.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE as FajarRayaDatabase
        }
    }




}

