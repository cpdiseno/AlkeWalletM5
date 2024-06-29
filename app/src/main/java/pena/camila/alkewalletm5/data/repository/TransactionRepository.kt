package pena.camila.alkewalletm5.data.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pena.camila.alkewalletm5.api.ApiService
import pena.camila.alkewalletm5.data.dao.TransactionDao
import pena.camila.alkewalletm5.model.database.TransactionEntity
import pena.camila.alkewalletm5.model.network.PaginatedTransactionResponse



class TransactionRepository(
    private val transactionDao: TransactionDao,
    private val apiService: ApiService,
) {

    suspend fun fetchTransactionsFromApi(token: String): PaginatedTransactionResponse {
        return withContext(Dispatchers.IO) {
            apiService.getTransactions(token)
        }
    }

    suspend fun saveTransactions(transactions: List<TransactionEntity>) {
        transactionDao.insertTransactions(transactions)
        Log.d("TransactionRepository", "Transacciones guardadas en la base de datos")
    }

    suspend fun deleteAllTransactions() {
        transactionDao.deleteAll()
    }
}
