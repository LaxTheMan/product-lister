package com.example.productlister.common

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.productlister.ui.product_add.ProductAddScreen
import com.example.productlister.ui.product_edit.ProductEditScreen
import com.example.productlister.ui.product_list.ProductListScreen
import com.example.productlister.ui.product_add.ProductAddViewModel
import com.example.productlister.ui.product_list.ProductListViewModel

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.ProductListScreen.route
    ) {
        composable(route = Screen.ProductListScreen.route) {
            val viewModel = hiltViewModel<ProductListViewModel>()
            var state = viewModel.state.value
            ProductListScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
        composable(route = Screen.ProductAddScreen.route) {
            val viewModel = hiltViewModel<ProductAddViewModel>()
            var state = viewModel.state.value
            val eventFlow = viewModel.eventFlow
            ProductAddScreen(
                navController = navController,
                state = state,
                onEvent = viewModel::onEvent,
                eventFlow = eventFlow
            )
        }
        composable(route = Screen.ProductEditScreen.route) {
            ProductEditScreen()
        }
    }
}