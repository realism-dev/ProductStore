package dev.realism.productstore.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.realism.productstore.core.data.source.local.model.ProductItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductItemDao {
    @Query("SELECT * FROM item")
    fun getAllProductItems(): Flow<List<ProductItemEntity>>

    @Query("SELECT * FROM item WHERE id=:id")
    fun getProductItemById(id: Int): Flow<ProductItemEntity>

    @Insert
    suspend fun insertProductItem(productItemEntity: ProductItemEntity)

    @Update
    suspend fun updateProductItem(productItemEntity: ProductItemEntity)

    @Delete
    suspend fun deleteProductItem(productItemEntity: ProductItemEntity)
}
