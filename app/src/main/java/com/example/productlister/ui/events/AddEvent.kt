package com.example.productlister.ui.events

sealed class AddEvent{
    data class NameChanged(val name: String): AddEvent()
    data class PriceChanged(val price: String): AddEvent()
    data class PricePerGChanged(val pricePerG: String): AddEvent()
    data class NetWtChanged(val netWt: String): AddEvent()
    object SaveProduct: AddEvent()

}

