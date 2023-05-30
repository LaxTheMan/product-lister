package com.example.productlister.ui.viewmodel

import com.example.productlister.domain.model.Product

data class ProductListState(
    val products: List<Product> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)