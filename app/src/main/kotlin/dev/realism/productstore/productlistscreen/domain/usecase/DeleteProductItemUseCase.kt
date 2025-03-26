package dev.realism.productstore.productlistscreen.domain.usecase

import dev.realism.productstore.core.domain.model.ProductItem

interface DeleteProductItemUseCase {
    suspend fun deleteProductItem(productItem: ProductItem)
}