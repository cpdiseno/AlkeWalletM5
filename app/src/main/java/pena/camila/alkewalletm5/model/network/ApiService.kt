package pena.camila.alkewalletm5.model.network


import com.example.alkewalletfinal.model.network.DepositOrTransferRequest
import com.example.alkewalletfinal.model.network.DepositOrTransferResponse
import pena.camila.alkewalletm5.model.LoginRequest
import pena.camila.alkewalletm5.model.LoginResponse
import pena.camila.alkewalletm5.model.RegisterRequest
import pena.camila.alkewalletm5.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
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