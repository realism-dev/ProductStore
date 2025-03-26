package dev.realism.productstore.productlistscreen.domain.usecase

import dev.realism.productstore.core.domain.model.ProductItem

interface UpdateProductItemUseCase {
    suspend fun updateProductItem(productItem: ProductItem)
}