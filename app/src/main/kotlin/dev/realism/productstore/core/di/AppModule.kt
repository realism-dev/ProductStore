package dev.realism.productstore.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.realism.productstore.core.domain.repository.LocalDataSourceRepository
import dev.realism.productstore.productlistscreen.domain.usecase.DeleteProductItemUseCase
import dev.realism.productstore.productlistscreen.domain.usecase.GetAllProductItemsUseCase
import dev.realism.productstore.productlistscreen.domain.usecase.UpdateProductItemUseCase
import dev.realism.productstore.productlistscreen.domain.usecase.impl.DeleteProductItemUseCaseImpl
import dev.realism.productstore.productlistscreen.domain.usecase.impl.GetAllProductItemsUseCaseImpl
import dev.realism.productstore.productlistscreen.domain.usecase.impl.UpdateProductItemUseCaseImpl
import dev.realism.productstore.productlistscreen.presentation.ProductListScreenViewModel
import javax.inject.Singleton

@Module
class AppModule(private val appContext: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context = appContext

    @Provides
    @Singleton
    fun provideProductListScreenViewModel(
        delete: DeleteProductItemUseCase,
        getAll: GetAllProductItemsUseCase,
        update: UpdateProductItemUseCase
    ): ProductListScreenViewModel {
        return ProductListScreenViewModel(delete = delete, update = update, getAll = getAll)
    }

    @Provides
    @Singleton
    fun bindDeleteProductItemUseCase(repository: LocalDataSourceRepository): DeleteProductItemUseCase {
        return DeleteProductItemUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun bindGetAllProductItemsUseCase(repository: LocalDataSourceRepository): GetAllProductItemsUseCase {
        return GetAllProductItemsUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun bindUpdateProductItemUseCase(repository: LocalDataSourceRepository): UpdateProductItemUseCase {
        return UpdateProductItemUseCaseImpl(repository)
    }
}
