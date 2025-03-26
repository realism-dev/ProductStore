package dev.realism.productstore.core.data.mapper

import dev.realism.productstore.core.data.source.local.model.ProductItemEntity
import dev.realism.productstore.core.domain.model.ProductItem

fun ProductItemEntity.toProductItem() = ProductItem(id, name, time, tags, amount)

fun ProductItem.toProductItemEntity() = ProductItemEntity(id, name, time, tags, amount)
