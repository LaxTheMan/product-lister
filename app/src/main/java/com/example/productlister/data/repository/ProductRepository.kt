package com.example.productlister.data.repository

import androidx.lifecycle.LiveData
import com.example.productlister.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProducts(): Flow<List<Product>>

    suspend fun getProductById(id: Int): Product?

    suspend fun upsertProduct(product: Product)

    suspend fun deleteProduct(product: Product)
}