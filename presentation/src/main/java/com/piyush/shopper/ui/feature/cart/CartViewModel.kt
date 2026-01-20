package com.piyush.shopper.ui.feature.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyush.data.model.response.CartItem
import com.piyush.domain.model.CartItemModel
import com.piyush.domain.model.CartModel
import com.piyush.domain.network.ResultWrapper
import com.piyush.domain.usecase.GetCartUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(val cartUsecase: GetCartUseCase): ViewModel(){
    private val _uiState = MutableStateFlow<CartEvent>(CartEvent.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getCart()
    }

    fun getCart(){
        viewModelScope.launch {
            _uiState.value = CartEvent.Loading
            cartUsecase.execute().let { result ->
                when(result) {
                    is ResultWrapper.Success -> {
                        _uiState.value = CartEvent.Success(result.value.data)
                    }

                    is ResultWrapper.Failure -> {
                        _uiState.value = CartEvent.Error("Something Went Wrong!")
                    }
                }
            }
        }
    }
}

sealed class CartEvent {

    object Loading: CartEvent()
    data class Success(val message: List<CartItemModel>): CartEvent()
    data class Error(val message: String): CartEvent()


}