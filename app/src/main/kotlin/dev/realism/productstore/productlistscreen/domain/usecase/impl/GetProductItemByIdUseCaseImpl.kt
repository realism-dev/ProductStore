package dev.realism.productstore.productlistscreen.domain.usecase.impl

import dev.realism.productstore.core.domain.model.ProductItem
import dev.realism.productstore.core.domain.repository.LocalDataSourceRepository
import dev.realism.productstore.productlistscreen.domain.usecase.GetProductItemByIdUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductItemByIdUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : GetProductItemByIdUseCase {
    override fun getProductItemById(id: Int): Flow<ProductItem> {
        return localDataSourceRepository.getProductItemByIdFlow(id)
    }
}