package dev.realism.productstore.core.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.realism.productstore.core.data.source.local.dao.ProductItemDao
import dev.realism.productstore.core.data.source.local.model.ProductItemEntity

@Database(entities = [ProductItemEntity::class], version = 1)
abstract class ProductStoreRoomDatabase: RoomDatabase() {
    abstract fun productItemDao() : ProductItemDao
}
