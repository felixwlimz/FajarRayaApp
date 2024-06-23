package com.fajarraya.app

import android.app.Application
import com.fajarraya.app.core.di.databaseModule
import com.fajarraya.app.core.di.firebaseModule
import com.fajarraya.app.core.di.repositoryModule
import com.fajarraya.app.di.useCaseModule
import com.fajarraya.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FajarRayaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@FajarRayaApplication)
            modules(
                listOf(
                    databaseModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                    firebaseModule
                )
            )
        }
    }

}