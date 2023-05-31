package com.example.productlister.domain.repository

import androidx.lifecycle.LiveData
import com.example.productlister.domain.model.Product
import kotlinx.coroutines.flow.Flow

// acts as single source of truth, for any data access/modify repository is used instead of directly accessing database
interface ProductRepository {

    fun getProducts(): Flow<List<Product>>

    suspend fun getProductById(id: Int): Product?

    suspend fun upsertProduct(product: Product)

    suspend fun deleteProduct(product: Product)
}