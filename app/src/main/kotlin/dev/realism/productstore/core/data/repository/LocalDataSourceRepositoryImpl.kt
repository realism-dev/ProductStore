package dev.realism.productstore.core.data.repository

import dev.realism.productstore.core.data.source.local.LocalDataSource
import dev.realism.productstore.core.domain.model.ProductItem
import dev.realism.productstore.core.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
) : LocalDataSourceRepository {
    override fun getAllProductItemsFlow(): Flow<List<ProductItem>> =
        localDataSource.getAllProductItems()

    override fun getProductItemByIdFlow(id: Int): Flow<ProductItem> =
        localDataSource.getProductItemByIdFlow(id)

    override suspend fun addProductItem(productItem: ProductItem) =
        localDataSource.addProductItem(productItem)

    override suspend fun updateProductItem(productItem: ProductItem) =
        localDataSource.updateProductItem(productItem)

    override suspend fun deleteProductItem(productItem: ProductItem) =
        localDataSource.deleteProductItem(productItem)
}
