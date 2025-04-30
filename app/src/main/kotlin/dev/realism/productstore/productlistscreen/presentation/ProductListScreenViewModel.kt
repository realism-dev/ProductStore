package dev.realism.productstore.productlistscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.realism.productstore.core.data.source.local.LocalDataSource
import dev.realism.productstore.core.domain.model.ProductItem
import dev.realism.productstore.core.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListScreenViewModel @Inject constructor(val repository: LocalDataSourceRepository) :
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
        return repository.getAllProductItemsFlow(searchQuery)
    }

    override suspend fun updateProductItem(productItem: ProductItem) {
        repository.updateProductItem(productItem)
    }

    override suspend fun deleteProductItem(productItem: ProductItem) {
        repository.deleteProductItem(productItem)
    }
}