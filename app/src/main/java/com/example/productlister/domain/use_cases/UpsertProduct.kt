package com.example.productlister.domain.use_cases

import com.example.productlister.domain.repository.ProductRepository
import com.example.productlister.domain.model.Product
import javax.inject.Inject

class UpsertProduct @Inject constructor(private val repository: ProductRepository){

    suspend operator fun invoke(product:Product) {
        repository.upsertProduct(product)
    }
}