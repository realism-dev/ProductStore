package dev.realism.productstore.productlistscreen.domain.usecase.impl

import dev.realism.productstore.core.domain.model.ProductItem
import dev.realism.productstore.core.domain.repository.LocalDataSourceRepository
import dev.realism.productstore.productlistscreen.domain.usecase.GetAllProductItemsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductItemsUseCaseImpl @Inject constructor(
    private val localDataSourceRepository: LocalDataSourceRepository
) : GetAllProductItemsUseCase {
    override fun getAllProductItems(): Flow<List<ProductItem>> {
        return localDataSourceRepository.getAllProductItemsFlow()
    }
}