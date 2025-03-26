package dev.realism.productstore.core.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class ProductItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "time")
    val time: Long,

    @ColumnInfo(name = "tags")
    val tags: String,

    @ColumnInfo(name = "amount")
    val amount: Int
)
