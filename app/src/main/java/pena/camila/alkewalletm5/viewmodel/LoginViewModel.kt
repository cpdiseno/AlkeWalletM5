package pena.camila.alkewalletm5.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pena.camila.alkewalletm5.api.ApiClient
import pena.camila.alkewalletm5.model.database.TransactionEntity
import pena.camila.alkewalletm5.model.network.AccountResponse
import pena.camila.alkewalletm5.model.network.CreateAccountResponse
import pena.camila.alkewalletm5.model.network.LoginRequest
import pena.camila.alkewalletm5.model.network.LoginResponse
import pena.camila.alkewalletm5.model.network.LoginService
import pena.camila.alkewalletm5.model.network.Retrofitinstance
import pena.camila.alkewalletm5.model.network.User
import pena.camila.alkewalletm5.utils.SharedPreferencesManager
import pena.camila.alkewalletm5.utils.TransactionFetcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class LoginViewModel(
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val transactionFetcher: TransactionFetcher? = null
) : ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    private val _userDetails = MutableLiveData<User>()

    private val _createAccountResult = MutableLiveData<Boolean>()
    val createAccountResult: LiveData<Boolean> get() = _createAccountResult

    private val _updateAccountResult = MutableLiveData<Boolean>()
    val updateAccountResult: LiveData<Boolean> get() = _updateAccountResult

    private val _accountDetailsUpdated = MutableLiveData<Boolean>()
    val accountDetailsUpdated: LiveData<Boolean> get() = _accountDetailsUpdated

    private val transactions = MutableLiveData<List<TransactionEntity>?>()

    fun login(email: String, password: String, isSignUp: Boolean = false) {
        val loginRequest = LoginRequest(email, password)
        ApiClient.apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    val token = response.body()?.accessToken
                    if (token != null) {
                        Log.d("LoginViewModel", "Login successful, token: $token")
                        sharedPreferencesManager.saveAuthToken(token)
                        getUserDetails(token, isSignUp)
                    } else {
                        Log.d("LoginViewModel", "Login response successful but token is null")
                        _loginResult.value = false
                    }
                } else {
                    Log.d("LoginViewModel", "Login response not successful: ${response.errorBody()?.string()}")
                    _loginResult.value = false
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("LoginViewModel", "Login request failed: ${t.message}")
                _loginResult.value = false
            }
        })
    }

    private fun getUserDetails(token: String, isSignUp: Boolean) {
        ApiClient.apiService.getUserDetails("Bearer $token").enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let {
                        Log.d("LoginViewModel", "Get user details successful for user: ${it.roleId}")
                        if (isSignUp) {
                            createAccount(token)
                        }
                        getUserAccountsDetails(token)
                        sharedPreferencesManager.saveUser(it)

                        _userDetails.value = it
                    }
                } else {
                    Log.d("LoginViewModel", "Get user details response not successful: ${response.errorBody()?.string()}")
                    _loginResult.value = false
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("LoginViewModel", "Get user details request failed: ${t.message}")
                _loginResult.value = false
            }
        })
    }

    private fun createAccount(token: String) {
        ApiClient.apiService.createAccount("Bearer $token")
            .enqueue(object : Callback<CreateAccountResponse> {
                override fun onResponse(
                    call: Call<CreateAccountResponse>,
                    response: Response<CreateAccountResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("LoginViewModel", "Account creation successful")
                        _createAccountResult.value = true
                    } else {
                        Log.d("LoginViewModel", "Account creation response not successful: ${response.errorBody()?.string()}")
                        _createAccountResult.value = false
                    }
                }

                override fun onFailure(call: Call<CreateAccountResponse>, t: Throwable) {
                    Log.d("LoginViewModel", "Account creation request failed: ${t.message}")
                    _createAccountResult.value = false
                }
            })
    }

    fun getUserAccountsDetails(token: String) {
        ApiClient.apiService.getUserAccountsDetails("Bearer $token")
            .enqueue(object : Callback<List<AccountResponse>> {
                override fun onResponse(
                    call: Call<List<AccountResponse>>,
                    response: Response<List<AccountResponse>>
                ) {
                    if (response.isSuccessful) {
                        val accounts = response.body()
                        accounts?.let {
                            if (it.isNotEmpty()) {
                                val account = it[0]
                                val money = account.money?.toString() ?: "0"
                                sharedPreferencesManager.saveAccountData(money, account.id)
                                _accountDetailsUpdated.value = true
                            }
                        }
                        fetchTransactions(token)
                    } else {
                        Log.d("LoginViewModel", "Get user accounts details response not successful: ${response.errorBody()?.string()}")
                        _accountDetailsUpdated.value = false
                    }
                }

                override fun onFailure(call: Call<List<AccountResponse>>, t: Throwable) {
                    Log.d("LoginViewModel", "Get user accounts details request failed: ${t.message}")
                    _accountDetailsUpdated.value = false
                }
            })
    }

    private fun fetchTransactions(token: String) {
        transactionFetcher?.fetchTransactions(token) { success, transactionsList ->
            if (success) {
                Log.d("LoginViewModel", "Transactions fetched successfully: $transactionsList")
                transactions.postValue(transactionsList)
                _loginResult.postValue(true)
            } else {
                Log.d("LoginViewModel", "Failed to fetch transactions")
                _loginResult.postValue(false)
            }
        }
    }
}