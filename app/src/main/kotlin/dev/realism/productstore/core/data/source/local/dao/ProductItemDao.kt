package dev.realism.productstore.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import dev.realism.productstore.core.data.source.local.model.ProductItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductItemDao {
    @Query("SELECT * FROM item WHERE name LIKE '%' || :searchQuery || '%'")
    fun getAllProductItems(searchQuery: String): Flow<List<ProductItemEntity>>

    @Update
    suspend fun updateProductItem(productItemEntity: ProductItemEntity)

    @Delete
    suspend fun deleteProductItem(productItemEntity: ProductItemEntity)
}
