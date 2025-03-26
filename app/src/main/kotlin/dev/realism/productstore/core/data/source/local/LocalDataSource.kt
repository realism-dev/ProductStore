package dev.realism.productstore.core.data.source.local

import dev.realism.productstore.core.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllProductItems(): Flow<List<ProductItem>>
    fun getProductItemByIdFlow(id: Int): Flow<ProductItem>
    suspend fun addProductItem(productItem: ProductItem)
    suspend fun updateProductItem(productItem: ProductItem)
    suspend fun deleteProductItem(productItem: ProductItem)
}