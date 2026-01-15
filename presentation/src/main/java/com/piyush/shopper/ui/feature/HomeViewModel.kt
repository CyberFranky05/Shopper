package com.piyush.shopper.ui.feature

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.piyush.domain.model.Product
import com.piyush.domain.network.ResultWrapper
import com.piyush.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel (private val getProductUseCase: GetProductUseCase): ViewModel(){
    private val _uiState = MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getAllProducts()
    }

    private fun getAllProducts(){
        viewModelScope.launch {
            _uiState.value = HomeScreenUIEvents.Loading
            val featured = getProduct("electronics")
            val popularProducts = getProduct("jewelery")
            if(featured.isEmpty()|| popularProducts.isEmpty()){
                _uiState.value = HomeScreenUIEvents.Error("Failed to load products")
                return@launch
            }
            _uiState.value = HomeScreenUIEvents.Success(featured,popularProducts)
        }
    }


    private suspend fun getProduct(category: String): List<Product>{

            _uiState.value = HomeScreenUIEvents.Loading
            getProductUseCase.execute(category).let { result ->
                when(result){
                    is ResultWrapper.Success -> {
                        return (result).value
                    }

                    is ResultWrapper.Failure -> {
                        Log.d("HomeViewModel", "$${result}")
                       return  emptyList()
                    }
                }

            }
        }

}



sealed class HomeScreenUIEvents {
    data object Loading: HomeScreenUIEvents()
    data class Success(val featured: List<Product> , val popularProducts: List<Product>) : HomeScreenUIEvents()
    data class Error(val message: String) : HomeScreenUIEvents()
}