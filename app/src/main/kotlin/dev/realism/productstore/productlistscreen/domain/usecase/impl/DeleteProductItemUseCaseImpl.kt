package dev.realism.productstore.productlistscreen.domain.usecase.impl

import dev.realism.productstore.core.domain.model.ProductItem
import dev.realism.productstore.core.domain.repository.LocalDataSourceRepository
import dev.realism.productstore.productlistscreen.domain.usecase.DeleteProductItemUseCase
import javax.inject.Inject

class DeleteProductItemUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : DeleteProductItemUseCase {
    override suspend fun deleteProductItem(productItem: ProductItem) {
        localDataSourceRepository.deleteProductItem(productItem)
    }
}