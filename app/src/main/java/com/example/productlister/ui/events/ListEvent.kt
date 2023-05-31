package com.example.productlister.ui.events

import com.example.productlister.domain.model.Product

sealed class ListEvent {
    data class UpsertProduct(val product: Product): ListEvent()
    data class DeleteProduct(val product: Product) : ListEvent()
    data class RestoreProduct(val product: Product) : ListEvent()
}