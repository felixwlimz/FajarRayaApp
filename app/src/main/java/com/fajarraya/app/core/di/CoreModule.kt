package com.fajarraya.app.core.di

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder
import androidx.datastore.rxjava3.RxDataStore
import androidx.room.Room
import com.fajarraya.app.core.data.datastore.DarkModePreference
import com.fajarraya.app.core.data.local.FajarRayaDatabase
import com.fajarraya.app.core.data.local.datasource.ProductDataSource
import com.fajarraya.app.core.data.local.datasource.SupplierDataSource
import com.fajarraya.app.core.repository.auth.AuthRepository
import com.fajarraya.app.core.repository.auth.IAuthRepository
import com.fajarraya.app.core.repository.datastore.IPrefRepository
import com.fajarraya.app.core.repository.datastore.PrefRepository
import com.fajarraya.app.core.repository.products.IProductRepository
import com.fajarraya.app.core.repository.products.ProductRepository
import com.fajarraya.app.core.repository.supplier.ISupplierRepository
import com.fajarraya.app.core.repository.supplier.SupplierRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import kotlin.math.sin


private fun provideDatabase(context : Context) : FajarRayaDatabase {
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

    factory { get<FajarRayaDatabase>().supplierDao() }

    single {
        provideDatabase(androidContext())
    }

}

val repositoryModule = module {

    single { ProductDataSource(get()) }

    single { SupplierDataSource(get())}

    single <IProductRepository>{
        ProductRepository(get(),get(),get(),get())
    }
    single<IAuthRepository> {
        AuthRepository(get(), get())
    }

    single<IPrefRepository> {
        PrefRepository(get())
    }

    single<ISupplierRepository> {
        SupplierRepository(get())
    }

}

val firebaseModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseDatabase.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FirebaseStorage.getInstance() }
}

val dataStoreModule = module {
    single { provideDataStore(androidContext(), "darkTheme") }

    single { DarkModePreference(get()) }
}