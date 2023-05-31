package com.example.productlister.domain.use_cases

import com.example.productlister.domain.repository.ProductRepository
import com.example.productlister.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProducts @Inject constructor(private val repository: ProductRepository) {

    operator fun invoke(): Flow<List<Product>> = flow {
        repository.getProducts()
    }

}