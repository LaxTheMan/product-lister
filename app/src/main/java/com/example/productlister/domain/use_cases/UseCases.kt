package com.example.productlister.domain.use_cases

data class UseCases(
    val getProducts: GetProducts,
    val upsertProduct: UpsertProduct,
    val deleteProduct: DeleteProduct
)
