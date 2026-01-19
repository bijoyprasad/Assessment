package com.bijoy.qbytezassessment

import android.app.Application
import com.bijoy.qbytezassessment.data.database.AppDatabase
import com.bijoy.qbytezassessment.data.di.appModule
import com.bijoy.qbytezassessment.data.di.navModule
import com.bijoy.qbytezassessment.data.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                appModule,
                navModule,
                viewModelModule
            )
        }
    }
}