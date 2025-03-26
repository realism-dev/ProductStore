package dev.realism.productstore.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.realism.productstore.core.domain.repository.LocalDataSourceRepository
import dev.realism.productstore.productlistscreen.presentation.ProductListScreenViewModel
import javax.inject.Singleton

@Module
class AppModule(private val appContext: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context = appContext
    @Provides
    @Singleton
    fun provideProductListScreenViewModel(repository: LocalDataSourceRepository): ProductListScreenViewModel {
        return ProductListScreenViewModel(repository)
    }
}
