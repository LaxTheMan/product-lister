package com.example.productlister.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.productlister.ui.product_add.ProductAddScreen
import com.example.productlister.ui.product_edit.ProductEditScreen
import com.example.productlister.ui.product_list.ProductListScreen
import com.example.productlister.ui.product_add.ProductAddViewModel
import com.example.productlister.ui.product_list.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProductListScreen.route
    ) {
        composable(route = Screen.ProductListScreen.route) {
            val productListViewModel = hiltViewModel<ProductListViewModel>()
            val state = productListViewModel.state.value
            val productAddViewModel = hiltViewModel<ProductAddViewModel>()
            ProductListScreen(
                navController = navController,
                state = state,
                onEvent = productListViewModel::onEvent,
                eventFlow = productAddViewModel.eventFlow
            )
        }
        composable(route = Screen.ProductAddScreen.route) {
            val productAddViewModel = hiltViewModel<ProductAddViewModel>()
            val state = productAddViewModel.state.value
            ProductAddScreen(
                navController = navController,
                state = state,
                onEvent = productAddViewModel::onEvent,
                eventFlow = productAddViewModel.eventFlow
            )
        }
        composable(route = Screen.ProductEditScreen.route) {
            ProductEditScreen()
        }
    }
}