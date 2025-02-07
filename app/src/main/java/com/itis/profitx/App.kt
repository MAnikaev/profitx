package com.itis.profitx

import android.app.Application
import com.itis.core.coreModule
import com.itis.impl.analysisModule
import com.itis.impl.authModule
import com.itis.impl.expenseModule
import com.itis.impl.incomeModule
import com.itis.impl.profileModule
import com.itis.impl.registrationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(authModule, coreModule, registrationModule, incomeModule, expenseModule, analysisModule, profileModule))
        }
    }
}