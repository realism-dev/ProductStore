package dev.realism.productstore.core.di

import dagger.Component
import dev.realism.productstore.MainActivity
import dev.realism.productstore.core.data.source.local.di.LocalSourceModuleBinder
import dev.realism.productstore.core.data.source.local.di.LocalSourceModuleProvider
import dev.realism.productstore.productlistscreen.presentation.ProductListScreenViewModel
import javax.inject.Singleton

@Component(modules = [AppModule::class, LocalSourceModuleProvider::class, LocalSourceModuleBinder::class])
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(viewModel: ProductListScreenViewModel)
}