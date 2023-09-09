package com.example.productlister.ui.events

sealed class UiEvent {
    data class ShowSnackbar(val message: String): UiEvent()
    object SaveProduct: UiEvent()
}
