package dev.realism.productstore.productlistscreen.domain.usecase.impl

import dev.realism.productstore.core.domain.model.ProductItem
import dev.realism.productstore.core.domain.repository.LocalDataSourceRepository
import dev.realism.productstore.productlistscreen.domain.usecase.InsertProductItemUseCase
import javax.inject.Inject

class InsertProductItemUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : InsertProductItemUseCase {
    override suspend fun insertProductItem(productItem: ProductItem) {
        localDataSourceRepository.addProductItem(productItem)
    }
}