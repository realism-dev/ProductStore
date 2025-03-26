package dev.realism.productstore.productlistscreen.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import dev.realism.productstore.core.data.source.local.LocalDataSource
import dev.realism.productstore.core.domain.model.ProductItem
import dev.realism.productstore.core.domain.repository.LocalDataSourceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductListScreenViewModel @Inject constructor(val repository: LocalDataSourceRepository):ViewModel(),LocalDataSource {
    var counts = 0
    override fun getAllProductItems(): Flow<List<ProductItem>> {
        counts++
        Log.d("VIEWMODEL","Запросов: $counts")
       return repository.getAllProductItemsFlow()
    }

    override fun getProductItemByIdFlow(id: Int): Flow<ProductItem> {
        TODO("Not yet implemented")
    }

    override suspend fun addProductItem(productItem: ProductItem) {
        TODO("Not yet implemented")
    }

    override suspend fun updateProductItem(productItem: ProductItem) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProductItem(productItem: ProductItem) {
        TODO("Not yet implemented")
    }

}