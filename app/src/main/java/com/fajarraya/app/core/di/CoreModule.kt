package com.fajarraya.app.core.di

import android.content.Context

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder
import androidx.datastore.rxjava3.RxDataStore
import androidx.room.Room
import com.fajarraya.app.core.data.datastore.DarkModePreference
import com.fajarraya.app.core.data.local.LocalDataSource
import com.fajarraya.app.core.data.local.dao.FajarRayaDatabase
import com.fajarraya.app.core.repository.auth.AuthRepository
import com.fajarraya.app.core.repository.auth.IAuthRepository
import com.fajarraya.app.core.repository.products.IProductRepository
import com.fajarraya.app.core.repository.products.ProductRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


private fun provideDatabase(context : Context) : FajarRayaDatabase{
    return Room.databaseBuilder( context, FajarRayaDatabase::class.java, "fajar_raya")
        .createFromAsset("fajar_raya.db")
        .fallbackToDestructiveMigration()
        .build()
}

private fun provideDataStore(context: Context, prefName : String) : RxDataStore<Preferences> {
    return RxPreferenceDataStoreBuilder(
        context, prefName
    ).build()
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
    single<IAuthRepository> {
        AuthRepository(get(), get())
    }

}

val firebaseModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseDatabase.getInstance() }
}

val dataStoreModule = module {
    single { provideDataStore(androidContext(), "darkTheme") }

    single { DarkModePreference(get()) }
}