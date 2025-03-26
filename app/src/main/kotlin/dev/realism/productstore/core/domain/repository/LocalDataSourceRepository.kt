package dev.realism.productstore.core.domain.repository

import dev.realism.productstore.core.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceRepository {
    fun getAllProductItemsFlow(): Flow<List<ProductItem>>
    fun getProductItemByIdFlow(id: Int): Flow<ProductItem>
    suspend fun addProductItem(productItem: ProductItem)
    suspend fun updateProductItem(productItem: ProductItem)
    suspend fun deleteProductItem(productItem: ProductItem)
}