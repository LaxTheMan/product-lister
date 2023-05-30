package com.example.productlister.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productlister.domain.model.Product
import com.example.productlister.domain.use_cases.UseCases
import com.example.productlister.util.AddEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ProductAddViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    private val _state = mutableStateOf(ProductAddState())
    var state: State<ProductAddState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: AddEvent) {
        when (event) {
            is AddEvent.NameChanged -> {
                _state.value = _state.value.copy(name = event.name)
            }

            is AddEvent.PriceChanged -> {
                _state.value = _state.value.copy(price = if(event.price.isBlank()) null else event.price.toInt())
            }

            is AddEvent.PricePerGChanged -> {
                _state.value = _state.value.copy(pricePerG = if(event.pricePerG.isBlank()) null else event.pricePerG.toFloat())
            }

            is AddEvent.NetWtChanged -> {
                _state.value = _state.value.copy(netWt = if(event.netWt.isBlank()) null else event.netWt.toInt())
            }

            is AddEvent.SaveProduct -> {
                Log.d("debug","save called")
                viewModelScope.launch {
                    try {
                        useCases.upsertProduct(
                            Product(
                                id = null,
                                name = state.value.name,
                                price = state.value.price!!,
                                netWt = state.value.netWt!!,
                                pricePerG = state.value.pricePerG!!,
                                img1 = state.value.img1,
                                img2 = state.value.img2,
                                img3 = state.value.img3,
                                dateAdded = Date(),
                                lastAccessed = Date()
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveProduct)
                        _eventFlow.emit(UiEvent.ShowSnackbar(message = "Product added"))
                    } catch (e: Exception) {
                        _eventFlow.emit(UiEvent.ShowSnackbar(message = "Unable to add product"))
                        Log.d("error", e.toString())
                    }
                }
            }
        }
    }

    // to trigger ui related events from viewmodel layer, like showing snackbar
    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveProduct: UiEvent()
    }
}