package com.piyush.data.network

import android.util.Log
import com.piyush.data.model.DataProductModel
import com.piyush.data.model.request.AddToCartRequest
import com.piyush.data.model.request.AddressDataModel
import com.piyush.data.model.request.LoginRequest
import com.piyush.data.model.request.RegisterRequest
import com.piyush.data.model.response.CartResponse
import com.piyush.data.model.response.CartSummaryResponse
import com.piyush.data.model.response.OrdersListResponse
import com.piyush.data.model.response.PlaceOrderResponse
import com.piyush.data.model.response.UserAuthResponse
import com.piyush.domain.model.AddressDomainModel
import com.piyush.domain.model.CartItemModel
import com.piyush.domain.model.CartModel
import com.piyush.domain.model.CartSummary
import com.piyush.domain.model.OrdersListModel
import com.piyush.domain.model.Product
import com.piyush.domain.model.UserDomainModel
import com.piyush.domain.model.request.AddCartRequestModel
import com.piyush.domain.model.response.CategoryResponse
import com.piyush.domain.model.response.ProductResponse
import com.piyush.domain.network.NetworkService
import com.piyush.domain.network.ResultWrapper
import com.piyush.domain.usecase.AddToCartUseCase
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.utils.io.InternalAPI
import kotlinx.io.IOException


class NetworkServiceImpl(val client: HttpClient) : NetworkService {
    private val baseUrl = "https://ecommerce-ktor-4641e7ff1b63.herokuapp.com/v2"
    override suspend fun getProducts(category: Int?): ResultWrapper<ProductResponse>{
        val url =
        if (category != null) "$baseUrl/products/category/$category" else "$baseUrl/products"


        val output =  makeWebRequest<ProductResponse , ProductResponse>(
            url = url,
            method = HttpMethod.Get,

        )
        Log.d("NetworkServiceImpl", "Fetched products for category $category: $output")
        return  output
    }

    override suspend fun getCategories(): ResultWrapper<CategoryResponse> {
        val url = "$baseUrl/categories"
        return makeWebRequest<CategoryResponse, CategoryResponse>(
            url = url,
            method = HttpMethod.Get
        )
    }

    override suspend fun addProductToCart(request: AddCartRequestModel, userId: Long): ResultWrapper<CartModel> {
        val url = "$baseUrl/cart/${userId}"
        return makeWebRequest(url = url,
                method = HttpMethod.Post,
                body = AddToCartRequest.fromCartRequestModel(request),
                mapper = { cartItem: CartResponse ->
                    cartItem.toCartModel()
                }
            )
    }

    override suspend fun getCart(userId: Long): ResultWrapper<CartModel> {
        val url = "$baseUrl/cart/${userId}"
        return makeWebRequest(url = url,
            method = HttpMethod.Get,
            mapper = { cartItem: CartResponse ->
                cartItem.toCartModel()
            }
        )
    }

    override suspend fun updateQuantity(cartItemModel: CartItemModel , userId: Long): ResultWrapper<CartModel> {
        val url = "$baseUrl/cart/$userId/${cartItemModel.id}"
        return makeWebRequest(url = url,
            method = HttpMethod.Put,
            body = AddToCartRequest(
                productId = cartItemModel.productId,
                quantity = cartItemModel.quantity
            ),
            mapper = { cartItem: CartResponse ->
                cartItem.toCartModel()
            })
    }

    override suspend fun deleteProduct(cartItemId: Int , userId: Long): ResultWrapper<CartModel> {
        val url = "$baseUrl/cart/$userId/$cartItemId"
        return makeWebRequest(url = url,
            method = HttpMethod.Delete,
            mapper = { cartItem: CartResponse ->
                cartItem.toCartModel()
            })

    }

    override suspend fun getCartSummary(userId: Long): ResultWrapper<CartSummary> {
        val url = "$baseUrl/checkout/$userId/summary"
        return makeWebRequest(url = url,
            method = HttpMethod.Get,
            mapper = { cartSummary: CartSummaryResponse ->
                cartSummary.toCartSummary()
            })

    }

    override suspend fun placeOrder(address: AddressDomainModel, userId: Long): ResultWrapper<Long> {
        val dataModel = AddressDataModel.fromDomainAddress(address)
        val url = "$baseUrl/orders/$userId"
        return makeWebRequest(url = url,
            method = HttpMethod.Post,
            body = dataModel,
            mapper = { orderRes: PlaceOrderResponse ->
                orderRes.data.id
            })
    }

    override suspend fun getOrderList(userId: Long): ResultWrapper<OrdersListModel> {
        val url = "$baseUrl/orders/$userId"
        return makeWebRequest(url = url,
            method = HttpMethod.Get,
            mapper = { ordersResponse: OrdersListResponse ->
                ordersResponse.toDomainResponse()
            })
    }


    override suspend fun login(email: String, password: String): ResultWrapper<com.piyush.domain.model.UserDomainModel> {
        val url = "$baseUrl/login"
        return makeWebRequest( url = url ,
            method = HttpMethod.Post,
            body = LoginRequest(email, password),
            mapper = { user: UserAuthResponse ->
                    user.data.toDomainModel()
                    }
                )
    }

    override suspend fun register(
        email: String,
        password: String,
        name: String
    ): ResultWrapper<UserDomainModel> {
        val url = "$baseUrl/signup"

        return makeWebRequest(
            url = url,
            method = HttpMethod.Post,
            body = RegisterRequest(email, password, name),
            mapper = { user: UserAuthResponse ->
                user.data.toDomainModel()
            }
        )

    }


    suspend inline fun <reified T, R> makeWebRequest(
        url: String,
        method: HttpMethod,
        body: Any? = null,
        headers: Map<String, String> = emptyMap(),
        parameters: Map<String, String> = emptyMap(),
        noinline mapper: ((T) -> R)? = null
    ): ResultWrapper<R> {
        return try {
            val response = client.request(url) {
                this.method = method
                // Apply query parameters
                url {
                    this.parameters.appendAll(Parameters.build {
                        parameters.forEach { (key, value) ->
                            append(key, value)
                        }
                    })
                }
                // Apply headers
                headers.forEach { (key, value) ->
                    header(key, value)
                }
                // Set body for POST, PUT, etc.
                if (body != null) {
                    setBody(body)
                }

                // Set content type
                contentType(ContentType.Application.Json)
            }.body<T>()
            val result: R = mapper?.invoke(response) ?: response as R
            ResultWrapper.Success(result)
        } catch (e: ClientRequestException) {
            ResultWrapper.Failure(e)
        } catch (e: ServerResponseException) {
            ResultWrapper.Failure(e)
        } catch (e: IOException) {
            ResultWrapper.Failure(e)
        } catch (e: Exception) {
            ResultWrapper.Failure(e)
        }
    }

}