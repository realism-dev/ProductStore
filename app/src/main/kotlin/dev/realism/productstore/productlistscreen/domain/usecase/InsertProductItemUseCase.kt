package dev.realism.productstore.productlistscreen.domain.usecase

import dev.realism.productstore.core.domain.model.ProductItem

interface InsertProductItemUseCase {
    suspend fun insertProductItem(productItem: ProductItem)
}