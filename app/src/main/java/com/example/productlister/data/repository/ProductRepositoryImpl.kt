package com.example.productlister.data.repository

import androidx.lifecycle.LiveData
import com.example.productlister.data.ProductDao
import com.example.productlister.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val dao: ProductDao) : ProductRepository {
    override fun getProducts(): Flow<List<Product>> {
        return dao.getProducts()
    }

    override suspend fun getProductById(id: Int): Product? {
        return dao.getProductById(id)
    }

    override suspend fun upsertProduct(product: Product) {
        return dao.upsertProduct(product)
    }

    override suspend fun deleteProduct(product: Product) {
        return dao.deleteProduct(product)
    }
}