package com.fajarraya.app.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fajarraya.app.core.data.local.entity.ProductEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface FajarRayaDao {

   @Query("SELECT * FROM fajar_raya")
   fun getAllProducts() : Flowable<List<ProductEntity>>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   fun insertProduct(productEntity: ProductEntity) : Completable

   @Delete
   fun deleteProduct(productEntity: ProductEntity) : Completable

   @Query("SELECT * FROM fajar_raya WHERE kode_barang =:kodeBarang")
   fun getProduct(kodeBarang : String) : Flowable<ProductEntity>

}