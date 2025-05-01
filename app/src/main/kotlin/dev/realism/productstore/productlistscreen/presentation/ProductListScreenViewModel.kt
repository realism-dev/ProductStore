package dev.realism.productstore.productlistscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.realism.productstore.core.data.source.local.LocalDataSource
import dev.realism.productstore.core.domain.model.ProductItem
import dev.realism.productstore.productlistscreen.domain.usecase.DeleteProductItemUseCase
import dev.realism.productstore.productlistscreen.domain.usecase.GetAllProductItemsUseCase
import dev.realism.productstore.productlistscreen.domain.usecase.UpdateProductItemUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListScreenViewModel @Inject constructor(
    private val delete:DeleteProductItemUseCase,
    private val getAll:GetAllProductItemsUseCase,
    private val update:UpdateProductItemUseCase
) :
    ViewModel(), LocalDataSource {
    private var _productList = MutableStateFlow<List<ProductItem>>(emptyList())
    val productList: StateFlow<List<ProductItem>> = _productList

    init {
        updateProductList()
    }

    fun updateProductList(searchQuery: String = "") {
        viewModelScope.launch {
            getAllProductItems(searchQuery).collect {
                _productList.value = it
            }
        }
    }

    override suspend fun getAllProductItems(searchQuery: String): Flow<List<ProductItem>> {
        return getAll(searchQuery)
    }

    override suspend fun updateProductItem(productItem: ProductItem) {
        update(productItem)
    }

    override suspend fun deleteProductItem(productItem: ProductItem) {
        delete(productItem)
    }
}