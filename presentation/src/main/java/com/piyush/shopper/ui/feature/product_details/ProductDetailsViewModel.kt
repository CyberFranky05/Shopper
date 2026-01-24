package com.piyush.shopper.ui.feature.product_details

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyush.domain.model.request.AddCartRequestModel
import com.piyush.domain.network.ResultWrapper
import com.piyush.domain.usecase.AddToCartUseCase
import com.piyush.shopper.ShopperSession
import com.piyush.shopper.model.UiProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel (val usecase : AddToCartUseCase): ViewModel(){


    private val _state = MutableStateFlow<ProductDetailEvent>(ProductDetailEvent.Nothing)
    val state = _state.asStateFlow()

    val userDomainModel  = ShopperSession.getUser()

    fun addProductToCart(product: UiProductModel){
        viewModelScope.launch {
            _state.value = ProductDetailEvent.Loading
            val result = usecase.execute(
                AddCartRequestModel(
                    product.id,
                    product.title,
                    product.price,
                    1,
                    userDomainModel!!.id!!
                ),
                userDomainModel.id!!.toLong()
            )
            when(result){
                is ResultWrapper.Success -> {
                    _state.value = ProductDetailEvent.Success("Product added to Cart")
                    Log.d("ProductDetailsViewModel", "Product added to cart: ${result.value}")
                }

                is ResultWrapper.Failure -> {
                    _state.value = ProductDetailEvent.Error("Something went wrong")
                    Log.d("ProductDetailsViewModel", "Product added to cart: ${result}")
                }
            }
        }
    }

}




sealed class ProductDetailEvent {
    data object Loading: ProductDetailEvent()
    data object Nothing: ProductDetailEvent()
    data class Success(val message: String) : ProductDetailEvent()
    data class Error(val message: String): ProductDetailEvent()
}