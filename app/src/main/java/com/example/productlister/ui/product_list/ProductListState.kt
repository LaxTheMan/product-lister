package com.example.productlister.ui.product_list

import com.example.productlister.domain.model.Product

data class ProductListState(
    var products: List<Product> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)