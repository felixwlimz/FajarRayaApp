package com.fajarraya.app.di

import android.content.Context
import com.fajarraya.app.core.repository.ProductRepository
import com.fajarraya.app.core.data.local.LocalDataSource
import com.fajarraya.app.core.data.local.dao.FajarRayaDatabase
import com.fajarraya.app.core.domain.usecase.ProductInteractor
import com.fajarraya.app.core.domain.usecase.ProductUseCase
import com.fajarraya.app.core.repository.IProductRepository

object Injection {


    private fun provideProductRepository(context : Context) : IProductRepository {
        val db = FajarRayaDatabase.getDatabase(context)
        val localDataSource = LocalDataSource.getInstance(db.productDao())
        return ProductRepository.getInstance(localDataSource)
    }

    fun provideProductUseCase(context: Context) : ProductUseCase{
        val repository = provideProductRepository(context)
        return ProductInteractor(repository)
    }


}


