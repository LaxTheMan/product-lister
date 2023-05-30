package com.example.productlister.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productlister.domain.model.Product
import com.example.productlister.domain.use_cases.UseCases
import com.example.productlister.util.ListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    private val _state = mutableStateOf(ProductListState())
    val state: State<ProductListState> = _state

    private var recentlyDeletedProduct: Product? = null
    private var getProductsJob: Job? = null

    init {
        getProducts()
    }

    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.DeleteProduct -> {
                viewModelScope.launch {
                    useCases.deleteProduct(event.product)
                    recentlyDeletedProduct = event.product
                }
            }

            is ListEvent.UpsertProduct -> {
                viewModelScope.launch {
                    useCases.upsertProduct(event.product)
                }
            }

            is ListEvent.RestoreProduct -> {
                viewModelScope.launch {
                    useCases.upsertProduct(recentlyDeletedProduct?: return@launch)
                    recentlyDeletedProduct = null
                }

            }
        }
    }

    private fun getProducts() {
        getProductsJob?.cancel()
        getProductsJob = useCases.getProducts().launchIn(viewModelScope)
    }

}