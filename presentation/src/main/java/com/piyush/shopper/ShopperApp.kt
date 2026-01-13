package com.piyush.shopper

import android.app.Application
import com.piyush.data.di.dataModule
import com.piyush.domain.di.domainModule
import com.piyush.shopper.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShopperApp: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShopperApp)
            modules(listOf(
                presentationModule,
                dataModule,
                domainModule
            ))
        }
    }
}