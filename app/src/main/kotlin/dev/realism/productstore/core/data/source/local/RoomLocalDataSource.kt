package dev.realism.productstore.core.data.source.local

import dev.realism.productstore.core.data.mapper.toProductItem
import dev.realism.productstore.core.data.mapper.toProductItemEntity
import dev.realism.productstore.core.data.source.local.dao.ProductItemDao
import dev.realism.productstore.core.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomLocalDataSource @Inject constructor(
    private val productItemDao: ProductItemDao
) : LocalDataSource {

    override fun getAllProductItems(): Flow<List<ProductItem>> {
        return productItemDao.getAllProductItems().map { productItemEntityList ->
                productItemEntityList.map { productItemEntity -> productItemEntity.toProductItem() }
            }
    }

    override fun getProductItemByIdFlow(id: Int): Flow<ProductItem> {
        return productItemDao.getProductItemById(id).map { productItemEntity ->
            productItemEntity.toProductItem()
        }
    }

    override suspend fun addProductItem(productItem: ProductItem) {
        productItemDao.insertProductItem(productItem.toProductItemEntity())
    }

    override suspend fun deleteProductItem(productItem: ProductItem) {
        productItemDao.deleteProductItem(productItem.toProductItemEntity())
    }

    override suspend fun updateProductItem(productItem: ProductItem) {
        productItemDao.updateProductItem(productItem.toProductItemEntity())
    }


}
