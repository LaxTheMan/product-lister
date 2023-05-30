package com.example.productlister.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.productlister.domain.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM consumer_product")
    fun getProducts(): Flow<List<Product>>

    @Query("SELECT * FROM consumer_product WHERE id = :id")
    suspend fun getProductById(id: Int): Product?

    @Upsert
    suspend fun upsertProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)
}