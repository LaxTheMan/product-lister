package com.example.productlister.common

sealed class Screen(val route: String) {
    object ProductListScreen : Screen("product_list_screen")
    object ProductEditScreen : Screen("product_edit_screen")
    object ProductAddScreen : Screen("product_add_screen")
}
