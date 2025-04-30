package dev.realism.productstore.core.domain.repository

import dev.realism.productstore.core.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceRepository {
    suspend fun getAllProductItemsFlow(searchQuery: String): Flow<List<ProductItem>>
    suspend fun updateProductItem(productItem: ProductItem)
    suspend fun deleteProductItem(productItem: ProductItem)
}