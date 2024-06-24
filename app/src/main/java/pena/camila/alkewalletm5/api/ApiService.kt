package pena.camila.alkewalletm5.api

import pena.camila.alkewalletm5.model.network.LoginRequest
import pena.camila.alkewalletm5.model.network.LoginResponse
import pena.camila.alkewalletm5.model.network.RegisterRequest
import pena.camila.alkewalletm5.model.network.User
import pena.camila.alkewalletm5.model.network.AccountResponse
import pena.camila.alkewalletm5.model.network.CreateAccountResponse
import pena.camila.alkewalletm5.model.network.DepositOrTransferRequest
import pena.camila.alkewalletm5.model.network.DepositOrTransferResponse
import pena.camila.alkewalletm5.model.network.PaginatedTransactionResponse
import pena.camila.alkewalletm5.model.network.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("/auth/me")
    fun getUserDetails(@Header("Authorization") token: String): Call<User>

    @POST("/users")
    fun registerUser(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("/accounts")
    fun createAccount(@Header("Authorization") token: String): Call<CreateAccountResponse>

    @GET("/accounts/me")
    fun getUserAccountsDetails(@Header("Authorization") token: String): Call<List<AccountResponse>>

    @POST("/accounts/{id}")
    fun depositarOtransferir(
        @Header("Authorization") token: String,
        @Path("id") accountId: Long?,
        @Body request: DepositOrTransferRequest
    ): Call<DepositOrTransferResponse>

    @GET("/transactions")
    suspend fun getTransactions(@Header("Authorization") token: String): PaginatedTransactionResponse
}