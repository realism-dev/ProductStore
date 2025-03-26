package dev.realism.productstore.productlistscreen.domain.usecase.impl

import dev.realism.productstore.core.domain.model.ProductItem
import dev.realism.productstore.core.domain.repository.LocalDataSourceRepository
import dev.realism.productstore.productlistscreen.domain.usecase.UpdateProductItemUseCase
import javax.inject.Inject

class UpdateProductItemUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : UpdateProductItemUseCase {
    override suspend fun updateProductItem(productItem: ProductItem) {
        localDataSourceRepository.updateProductItem(productItem)
    }
}