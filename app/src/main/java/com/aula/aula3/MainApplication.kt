package com.aula.aula3

import android.app.Application
import com.aula.aula3.di.dataModule
import com.aula.aula3.di.networkModule
import com.aula.aula3.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    viewModelModule,
                    networkModule,
                    dataModule
                )
            )
            androidLogger()
            androidContext(this@MainApplication)
            androidLogger(Level.INFO)
        }
    }
}