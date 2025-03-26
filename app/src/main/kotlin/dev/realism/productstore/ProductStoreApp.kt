package dev.realism.productstore

import android.app.Application
import dev.realism.productstore.core.data.source.local.di.LocalSourceModuleProvider
import dev.realism.productstore.core.di.AppComponent
import dev.realism.productstore.core.di.AppModule
import dev.realism.productstore.core.di.DaggerAppComponent

class ProductStoreApp:Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .localSourceModuleProvider(LocalSourceModuleProvider())
            .build()
    }
}