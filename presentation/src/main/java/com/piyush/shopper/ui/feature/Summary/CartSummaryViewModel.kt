package com.piyush.shopper.ui.feature.Summary

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.piyush.data.model.Summary
import com.piyush.domain.model.CartSummary
import com.piyush.domain.network.ResultWrapper
import com.piyush.domain.usecase.CartSummaryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartSummaryViewModel (private val cartSummaryUseCase: CartSummaryUseCase): ViewModel() {

    private val _uiState = MutableStateFlow<CartSummaryEvent>(CartSummaryEvent.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getCartSummary(1)
    }

    private fun getCartSummary(userId: Int) {

        viewModelScope.launch  {
            _uiState.value = CartSummaryEvent.Loading
            val Summary = cartSummaryUseCase.execute(userId)

            when(Summary){
                is ResultWrapper.Success -> {
                    _uiState.value = CartSummaryEvent.Success(Summary.value)

                }

                is ResultWrapper.Failure -> {
                    _uiState.value = CartSummaryEvent.Error("Something Went Wrong!")
                    Log.d("TAG", "getCartSummary: ${Summary}")
                }
            }
        }


    }



}



sealed class CartSummaryEvent {
    data object Loading: CartSummaryEvent()
    data class Success(val cartSummary: CartSummary): CartSummaryEvent()
    data class Error(val message: String): CartSummaryEvent()
}
