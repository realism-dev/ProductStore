package dev.realism.productstore.core.data.source.local

import dev.realism.productstore.core.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getAllProductItems(searchQuery: String = ""): Flow<List<ProductItem>>
    suspend fun updateProductItem(productItem: ProductItem)
    suspend fun deleteProductItem(productItem: ProductItem)
}