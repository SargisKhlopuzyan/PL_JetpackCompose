package com.sargis.khlopuzyan.pl_jetpackcompose.ui

import android.app.Application
import com.sargis.khlopuzyan.data.di.dataModules
import com.sargis.khlopuzyan.domain.di.domainModule
import com.sargis.khlopuzyan.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class App : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModules + domainModule + presentationModule)
        }
    }
}