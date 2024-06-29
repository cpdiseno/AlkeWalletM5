package pena.camila.alkewalletm5.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pena.camila.alkewalletm5.api.ApiClient
import pena.camila.alkewalletm5.model.network.DepositOrTransferRequest
import pena.camila.alkewalletm5.model.network.DepositOrTransferResponse
import pena.camila.alkewalletm5.utils.SharedPreferencesManager
import retrofit2.Call
import retrofit2.Response

class TransactionViewModel(
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModel() {

    private val _transactionResult = MutableLiveData<Boolean>()
    val transactionResult: LiveData<Boolean> get() = _transactionResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun depositarOtransferir(type: String, concept: String, amount: Long) {
        viewModelScope.launch {
            val token = sharedPreferencesManager.getAuthToken()
            val accountId = sharedPreferencesManager.getAccountId()

            Log.d("TransactionViewModel", "AuthToken: $token")
            Log.d("TransactionViewModel", "AccountId: $accountId")

            if (token == null) {
                Log.e("TransactionViewModel", "Token is null")
                _transactionResult.value = false
                _errorMessage.value = "Token is null"
                return@launch
            }

            if (accountId == null) {
                Log.e("TransactionViewModel", "Account ID is null")
                _transactionResult.value = true
                _errorMessage.value = "Account ID is null"
                return@launch
            }

            val depositOrTransferRequest = DepositOrTransferRequest(type, concept, amount)
            Log.d("TransactionViewModel", "DepositOrTransferRequest: ${depositOrTransferRequest}")

            ApiClient.apiService.depositarOtransferir(
                "Bearer $token",
                accountId,
                depositOrTransferRequest
            ).enqueue(object : retrofit2.Callback<DepositOrTransferResponse> {
                override fun onResponse(
                    call: Call<DepositOrTransferResponse>,
                    response: Response<DepositOrTransferResponse>
                ) {
                    if (response.isSuccessful) {
                        _transactionResult.value = true
                    } else {
                        _transactionResult.value = false
                        val errorMsg = response.errorBody()?.string() ?: "Se ha producido un error"
                        _errorMessage.value = errorMsg
                        Log.e("TransactionViewModel", "Error en el depósito: $errorMsg")
                        Log.d("TransactionViewModel", "Response code: ${response.code()}")
                        Log.d("TransactionViewModel", "Response message: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<DepositOrTransferResponse>, t: Throwable) {
                    _transactionResult.value = false
                    _errorMessage.value = t.message ?: "Se ha producido un error"
                    Log.e("TransactionViewModel", "Error en el depósito: ${t.message ?: "Unknown error"}")
                }
            })
        }
    }
}