package com.fajarraya.app.core.di

import android.content.Context
import androidx.room.Room
import com.fajarraya.app.core.data.local.LocalDataSource
import com.fajarraya.app.core.data.local.dao.FajarRayaDatabase
import com.fajarraya.app.core.repository.IProductRepository
import com.fajarraya.app.core.repository.ProductRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


private fun provideDatabase(context : Context) : FajarRayaDatabase{
    return Room.databaseBuilder( context, FajarRayaDatabase::class.java, "fajar_raya")
        .createFromAsset("fajar_raya.db")
        .fallbackToDestructiveMigration()
        .build()
}




val databaseModule = module {

    factory { get<FajarRayaDatabase>().productDao() }

    single {
        provideDatabase(androidContext())
    }

}

val repositoryModule = module {

    single { LocalDataSource(get()) }
    single <IProductRepository>{
        ProductRepository(get())
    }

}