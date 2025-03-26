package dev.realism.productstore.productlistscreen.domain.usecase

import dev.realism.productstore.core.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow

interface GetProductItemByIdUseCase {
    fun getProductItemById(id: Int): Flow<ProductItem>
}