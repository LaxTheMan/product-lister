package com.example.productlister.domain.use_cases

import com.example.productlister.data.repository.ProductRepository
import com.example.productlister.domain.model.Product
import javax.inject.Inject

class DeleteProduct @Inject constructor(private val repository: ProductRepository) {

    suspend operator fun invoke(product:Product) {
        repository.deleteProduct(product)
    }

}