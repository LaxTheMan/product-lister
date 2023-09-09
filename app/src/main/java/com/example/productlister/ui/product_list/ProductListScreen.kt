package com.example.productlister.ui.product_list

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.productlister.common.Screen
import com.example.productlister.ui.product_list.components.ProductListItem
import com.example.productlister.ui.events.ListEvent
import com.example.productlister.ui.events.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    navController: NavController,
    state: ProductListState,
    onEvent: (ListEvent) -> Unit,
    eventFlow: SharedFlow<UiEvent>
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = "ListScreen") {
        eventFlow.collectLatest { event: UiEvent ->
            when (event) {
//                is UiEvent.ShowSnackbarHomeScreen -> {
//                    Log.d("debug","event block")
//                    snackbarHostState.showSnackbar(message = event.message)
//                }

                else -> {
//                    Log.d("debug","others")
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("App Title") })
        },
        content = { contentPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
//                Log.d("debug",state.products.toString())
                items(state.products) { product ->
                    ProductListItem(product = product, onEdit = {
                        navController.navigate(Screen.ProductEditScreen.route + "/${product.id}")
                    }, onDelete = {
                        onEvent(ListEvent.DeleteProduct(product))
                        scope.launch {
                            val result = snackbarHostState.showSnackbar(
                                message = "Product Deleted", actionLabel = "Undo"
                            )
                            if (result == SnackbarResult.ActionPerformed) {
                                onEvent(ListEvent.RestoreProduct(product))
                            }
                        }
                    })
                }
            }
            if (state.error.isNotBlank()) {
                Box {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            if (state.isLoading) {
                Box() {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {

                navController.navigate(Screen.ProductAddScreen.route)
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = {SnackbarHost(hostState = snackbarHostState)},
    )
}

@Preview
@Composable
fun ProductListScreenPreview() {
    ProductListScreen(rememberNavController(), state = ProductListState(emptyList(),"",false), onEvent = {}, eventFlow = MutableSharedFlow())
}