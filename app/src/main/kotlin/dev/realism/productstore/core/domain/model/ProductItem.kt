package dev.realism.productstore.core.domain.model

data class ProductItem(
    val id: Int = 0,
    val name: String,
    val time: Long,
    val tags: String,
    val amount: Int
)
