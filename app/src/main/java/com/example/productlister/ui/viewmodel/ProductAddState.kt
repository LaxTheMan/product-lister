package com.example.productlister.ui.viewmodel

data class ProductAddState(
    var name: String = "",
    var price: Int? = null,
    var pricePerG: Float? = null,
    var netWt: Int? = null,
    var img1: String = "",
    var img2: String? = "",
    var img3: String? = "",
    var error: String = ""
)